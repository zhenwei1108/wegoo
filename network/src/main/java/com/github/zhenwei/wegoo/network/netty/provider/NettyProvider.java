package com.github.zhenwei.wegoo.network.netty.provider;

import com.github.zhenwei.wegoo.common.enums.NetworkExceptionEnum;
import com.github.zhenwei.wegoo.common.exception.NetworkException;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.val;

/**
 * @description: 提供者
 * @date: 2021/12/23 22:38
 */
public abstract class NettyProvider {

  /**
   * NettyLoggerInfoAdapter.getLogLevel()
   *
   * @param initializer
   */
  public void provide(String host, int port, ChannelInitializer<SocketChannel> initializer,
      GenericFutureListener<ChannelPromise> listener, LogLevel level) throws NetworkException {
    try {
      val worker = new NioEventLoopGroup(1);
      val bootstrap = new Bootstrap();
      options(bootstrap).group(worker).channel(NioSocketChannel.class)
          .handler(new LoggingHandler(level))
          .handler(initializer).bind(host, port).sync().addListener(listener);
    } catch (Exception e) {
      throw new NetworkException(NetworkExceptionEnum.CLIENT_BIND_ERR, e);
    }
  }


  public Bootstrap options(Bootstrap bootstrap){
    option(bootstrap);
    return bootstrap;
  }

  /**
   * @param [serverBootstrap]
   * @return com.github.zhenwei.wegoo.network.netty.consumer.NettyConsumer
   * @description you can do everything from here
   * @auther zhangzhenwei
   * @date 2021/12/24 23:03
   */
  public abstract void option(Bootstrap bootstrap);


}