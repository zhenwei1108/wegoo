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
    NioEventLoopGroup worker = new NioEventLoopGroup();
    ServerBootstrap server = new ServerBootstrap().group(boss, worker)
        .channel(NioServerSocketChannel.class)
        .option(ChannelOption.AUTO_CLOSE, true)
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel sc) throws Exception {
            sc.pipeline().addLast(
                //IP 过滤
                new RuleBasedIpFilter(new IpWhitelistFilterRule()),
                //日志
                new LoggingHandler(LogLevel.TRACE),
                //接收消息 处理
                new ServerMessageDecoderHandler(),
                //应答消息处理
                new ServerMessageEncoderHandler()

            );
          }
        });
    ChannelFuture future = server.bind(port).sync();
    future.channel().closeFuture().sync();

  }

}