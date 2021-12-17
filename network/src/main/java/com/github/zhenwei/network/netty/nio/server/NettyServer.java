package com.github.zhenwei.network.netty.nio.server;

import com.github.zhenwei.network.netty.nio.proto.PersionEntity;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.AbstractChannel;
import io.netty.channel.AbstractChannelHandlerContext;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.channel.nio.AbstractNioMessageChannel.NioMessageUnsafe;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.ipfilter.RuleBasedIpFilter;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

public class NettyServer {


  public void server(int port) throws InterruptedException {
    NioEventLoopGroup boss = new NioEventLoopGroup(1);
    /**
     * NioEventLoop 默认数量: NettyRuntime.availableProcessors() * 2
     * CPU核心数量 * 2
     *  NioEventLoop 中包含 taskQueueFactory 任务队列工程
     *  每个 EventLoop 包含一个 selector,进行任务处理.由 next 方法将 handler 注册到selector上.
     *  父类是一个 SingleThreadEventExecutor, 会被触发执行 run() 方法.其中包含selectorkey操作
     *  核心请关注:{@linkplain NioEventLoop#run()} 其中 processSelectedKeys()方法选择key.
     *  根据 k.readyOps()获取不同的操作调用 unsafe(NioSocketChannelUnsafe) {@linkplain NioMessageUnsafe#read()}的不同方法.
     *  在read方法中.doReadMessages 进行accept()操作获取客户端链接的SocketChannel并存入list.
     *  执行 pipeline.fireChannelRead() {@linkplain io.netty.channel.AbstractChannelHandlerContext#invokeChannelRead(AbstractChannelHandlerContext, Object)}
     *  从而进入decode方法和read等方法.
     *  channelRead在{@linkplain ServerBootstrap.ServerBootstrapAcceptor#channelRead(ChannelHandlerContext, Object)} 有特定实现.
     *  进行客户端链接channel的注册.register操作实际为从worker线程中选择一个进行绑定(需关注next()方法).
     *  最终进入{@linkplain AbstractChannel.AbstractUnsafe#register(EventLoop, ChannelPromise)}
     *
     */
    NioEventLoopGroup worker = new NioEventLoopGroup();
    ServerBootstrap server = new ServerBootstrap().group(boss, worker)
        //为channelFactory设置构造方法,在bind方法去具体构建,反射对象.
        .channel(NioServerSocketChannel.class)
        .option(ChannelOption.AUTO_CLOSE, true)
        .option(ChannelOption.SO_BACKLOG, 256)
        //handler 为 boss group 适配.
        //日志
        .handler(new LoggingHandler(LogLevel.TRACE))
        // childHandler 为 worker 适配. 参考strap的group方法
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel sc) throws Exception {
            //拿到客户端链接的 channel.类似accept操作.
            /**
             * handler 被封装为了{@link io.netty.channel.DefaultChannelHandlerContext},存放进了pipline.
             * context是一个双向链表. 所以add的顺序不可以所以调节,避免影响消息处理方式
             * 在addLast操作时,{@link io.netty.channel.DefaultChannelPipeline#addLast(ChannelHandler...)}
             * 会通过 newContext 创建一个 DefaultChannelHandlerContext 进行绑定
             */
            sc.pipeline().addLast(

                /**
                 * 每个handler被封装为{@linkplain io.netty.channel.DefaultChannelHandlerContext}
                 * 而 ChannelHandlerContext 实现两个接口,分别为:
                 * @see io.netty.channel.ChannelInboundInvoker
                 * @see io.netty.channel.ChannelOutboundInvoker
                 *
                 * 在addLast操作中 根据
                 * {@linkplain AbstractChannelHandlerContext#findContextInbound(int)}
                 * {@linkplain AbstractChannelHandlerContext#findContextOutbound(int)}
                 *  链表调用流程, 建议,addLast最后为InboundHandler.中间为OutboundHandler 并注意排序.
                 */
                //IP 过滤
                new RuleBasedIpFilter(new IpWhitelistFilterRule()),
                //心跳检测 读3s,写 5s, 空闲 7s.
                //触发后,会转发给下一个handler 的 userEventTriggered 方法进行处理
                new IdleStateHandler(3, 5, 7, TimeUnit.SECONDS),
                //心跳处理
                new HeartbeatHandler(),
                //接收消息 处理
                new ServerMessageEncoderHandler(),
                //使用protbuf 进行对象传输
                new ProtobufDecoder(PersionEntity.Persion.getDefaultInstance()),
                //应答消息处理
                new ServerMessageDecoderHandler()

            );
          }
        });
    /**
     * 在{@linkplain io.netty.bootstrap.AbstractBootstrap#doBind(SocketAddress)}方法中.
     * initAndRegister 先实例化ChannelFactory. 后对参数进行配置.
     * 在doBind0中进行端口的绑定.
     */
    ChannelFuture future = server.bind(port).addListener(result -> {
      if (result.isSuccess()) {
        System.out.println("服务启动成功,使用端口：" + port);
      } else {
        System.out.println("服务启动失败");
        System.exit(0);
      }
    }).sync();
    future.channel().closeFuture().sync();
  }

}