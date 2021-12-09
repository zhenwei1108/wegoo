package com.github.zhenwei.network.netty.nio.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ipfilter.RuleBasedIpFilter;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyServer {


  public void server(int port) throws InterruptedException {
    NioEventLoopGroup boss = new NioEventLoopGroup(1);
    /**
     * NioEventLoop 默认数量: NettyRuntime.availableProcessors() * 2
     * CPU核心数量 * 2
     */
    NioEventLoopGroup worker = new NioEventLoopGroup();
    ServerBootstrap server = new ServerBootstrap().group(boss, worker)
        .channel(NioServerSocketChannel.class)
        .option(ChannelOption.AUTO_CLOSE, true)
        .option(ChannelOption.SO_BACKLOG,128)
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel sc) throws Exception {
            sc.pipeline().addLast(
                //日志
                new LoggingHandler(LogLevel.TRACE),
                //IP 过滤
                new RuleBasedIpFilter(new IpWhitelistFilterRule()),
                //接收消息 处理
                new ServerMessageEncoderHandler(),
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