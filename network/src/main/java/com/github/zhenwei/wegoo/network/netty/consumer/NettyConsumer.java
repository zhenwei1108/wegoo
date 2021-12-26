package com.github.zhenwei.wegoo.network.netty.consumer;

import com.github.zhenwei.wegoo.common.enums.NetworkExceptionEnum;
import com.github.zhenwei.wegoo.common.exception.NetworkException;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import lombok.val;

/**
 * @description: consumer 的抽象实现, 用于实现服务端
 * @author: zhangzhenwei
 * @date: 2021/12/26 21:46
 */
public abstract class NettyConsumer {

  private static final InternalLogger logger = InternalLoggerFactory.getInstance(
      NettyConsumer.class);

  /**
   * @param [workerSize, port, channelInitializer, listener, level]
   * @return void
   * @author zhangzhenwei
   * @description netty consumer bind port
   * @date 2021/12/26 21:46
   */
  public void consume(int workerSize, int port, ChannelInitializer<SocketChannel> channelInitializer,
      GenericFutureListener<ChannelPromise> listener, LogLevel level) throws NetworkException {

    try {
      //核心主线程 1 个
      NioEventLoopGroup boss = new NioEventLoopGroup(1),
          //默认业务处理线程 cpu*2
          worker = new NioEventLoopGroup(workerSize);
      logger.info("netty server will start to use port: {}",port);
      val serverBootstrap = new ServerBootstrap();

      val future = options(serverBootstrap).group(boss, worker)
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

  ServerBootstrap options(ServerBootstrap serverBootstrap){
    option(serverBootstrap);
    childOption(serverBootstrap);
    return serverBootstrap;
  }

  /**
   * @param [serverBootstrap]
   * @description you can do everything from here
   * @auther zhangzhenwei
   * @date 2021/12/24 23:03
   */
  public abstract void option(ServerBootstrap serverBootstrap);

  public abstract void childOption(ServerBootstrap serverBootstrap);

}