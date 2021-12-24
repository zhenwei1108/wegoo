package com.github.zhenwei.wegoo.network.netty.consumer;

import com.github.zhenwei.wegoo.common.enums.NetworkExceptionEnum;
import com.github.zhenwei.wegoo.common.exception.NetworkException;
import com.github.zhenwei.wegoo.network.netty.NettyChannelInitializer;
import com.github.zhenwei.wegoo.network.netty.consumer.listerner.NettyConsumerStartListener;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelPromise;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import lombok.val;

public abstract class NettyConsumer {

  private static final InternalLogger logger = InternalLoggerFactory.getInstance(
      NettyConsumerStartListener.class);




  public void consume(int workerSize, int port, NettyChannelInitializer channelInitializer,
      GenericFutureListener<ChannelPromise> listener, LogLevel level) throws NetworkException {

    try {
      //核心主线程 1 个
      NioEventLoopGroup boss = new NioEventLoopGroup(1),
          //默认业务处理线程 cpu*2
          worker = new NioEventLoopGroup(workerSize);

      val serverBootstrap = new ServerBootstrap();
      option(serverBootstrap);
      val future = serverBootstrap.group(boss, worker)
          .channel(NioServerSocketChannel.class)
          .handler(new LoggingHandler(level))
          .childHandler(channelInitializer).bind(port).sync()
          .addListener(listener);
      //同步等待退出
      future.channel().closeFuture().sync();
    } catch (Exception e) {
      throw new NetworkException(NetworkExceptionEnum.SERVER_START_ERR, e);
    }
  }

  abstract NettyConsumer option(ServerBootstrap serverBootstrap);

}