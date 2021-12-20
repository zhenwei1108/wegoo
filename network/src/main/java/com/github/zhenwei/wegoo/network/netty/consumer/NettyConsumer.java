package com.github.zhenwei.wegoo.network.netty.consumer;

import com.github.zhenwei.wegoo.common.enums.NetworkExceptionEnum;
import com.github.zhenwei.wegoo.common.exception.NetworkException;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public abstract class NettyConsumer {

  private static final InternalLogger logger = InternalLoggerFactory.getInstance(
      NettyConsumerStartListener.class);
  private ServerBootstrap serverBootstrap;

  public <T> NettyConsumer operate(ChannelOption<T> option, T value) throws NetworkException {
    if (serverBootstrap == null) {
      server();
    }
    serverBootstrap.option(option, value);
    return this;
  }

  private synchronized void server() {

    //核心主线程 1 个
    NioEventLoopGroup boss = new NioEventLoopGroup(1);
    //业务处理线程 cpu*2
    NioEventLoopGroup worker = new NioEventLoopGroup();

    this.serverBootstrap = new ServerBootstrap();

    serverBootstrap
        .group(boss, worker)
        .channel(NioServerSocketChannel.class)
        .handler(new LoggingHandler(NettyLoggerInfoAdapter.getLogLevel()))
        .childHandler(new NettyConsumerChannelInitializer());

  }


  public void run(int port) throws NetworkException {
    try {
      if (port == 0) {
        throw new NetworkException(NetworkExceptionEnum.SERVER_PORT_EMPTY_ERR);
      }
      ChannelFuture future = serverBootstrap.bind(port).sync()
          .addListener(new NettyConsumerStartListener());
      //同步等待退出
      future.channel().closeFuture().sync();
    } catch (Exception e) {
      throw new NetworkException(NetworkExceptionEnum.SERVER_START_ERR, e);
    }finally {

    }
  }
}