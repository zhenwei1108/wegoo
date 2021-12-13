package com.github.zhenwei.network.netty.nio.server;

import com.github.zhenwei.network.netty.nio.proto.PersionEntity;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.ipfilter.RuleBasedIpFilter;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;

public class NettyServer {


  public void server(int port) throws InterruptedException {
    NioEventLoopGroup boss = new NioEventLoopGroup(1);
    /**
     * NioEventLoop 默认数量: NettyRuntime.availableProcessors() * 2
     * CPU核心数量 * 2
     *  NioEventLoop 中包含 taskQueueFactory 任务队列工程
     *  每个EventLoop包含一个selector,进行任务处理.由next方法将handler注册到selector上.
     */
    NioEventLoopGroup worker = new NioEventLoopGroup();
    ServerBootstrap server = new ServerBootstrap().group(boss, worker)
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
             */
            sc.pipeline().addLast(

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