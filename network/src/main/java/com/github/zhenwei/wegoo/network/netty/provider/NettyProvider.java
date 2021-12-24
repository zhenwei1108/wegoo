package com.github.zhenwei.wegoo.network.netty.provider;

import com.github.zhenwei.wegoo.common.enums.NetworkExceptionEnum;
import com.github.zhenwei.wegoo.common.exception.NetworkException;
import com.github.zhenwei.wegoo.network.netty.NettyChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelPromise;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.GenericFutureListener;

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
  public void provide(String host, int port, NettyChannelInitializer initializer,
      GenericFutureListener<ChannelPromise> listener, LogLevel level) throws NetworkException {
    try {
      NioEventLoopGroup worker = new NioEventLoopGroup(1);
      Bootstrap bootstrap = new Bootstrap();
      option(bootstrap);
      bootstrap.group(worker).channel(NioSocketChannel.class)
          .handler(new LoggingHandler(level))
          .handler(initializer).bind(host, port).sync().addListener(listener);
    } catch (Exception e) {
      throw new NetworkException(NetworkExceptionEnum.CLIENT_BIND_ERR, e);
    }
  }


  abstract void option(Bootstrap bootstrap);


}