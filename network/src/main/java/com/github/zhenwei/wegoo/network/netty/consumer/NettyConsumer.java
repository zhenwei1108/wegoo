package com.github.zhenwei.wegoo.network.netty.consumer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class NettyConsumer {

  private static final InternalLogger logger = InternalLoggerFactory.getInstance(
      NettyConsumerStartListener.class);

  public void server(int port) throws InterruptedException {
    //核心主线程 1 个
    NioEventLoopGroup boss = new NioEventLoopGroup(1);
    //业务处理线程 cpu*2
    NioEventLoopGroup worker = new NioEventLoopGroup();

    ServerBootstrap serverBootstrap = new ServerBootstrap();
    ChannelFuture future = serverBootstrap
        .group(boss, worker)
        .channel(NioServerSocketChannel.class)
        .childOption(ChannelOption.AUTO_CLOSE, true)
        .handler(new LoggingHandler(NettyLoggerInfoAdapter.getLogLevel()))
        .childHandler(new NettyConsumerChannelInitializer())
        .bind(port).sync().addListener(new NettyConsumerStartListener());
    //同步等待退出
    future.channel().closeFuture().sync();
  }


}