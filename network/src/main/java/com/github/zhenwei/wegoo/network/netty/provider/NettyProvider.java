package com.github.zhenwei.wegoo.network.netty.provider;

import com.github.zhenwei.wegoo.common.enums.NetworkExceptionEnum;
import com.github.zhenwei.wegoo.common.exception.NetworkException;
import com.github.zhenwei.wegoo.network.netty.NettyChannelInitializer;
import com.github.zhenwei.wegoo.network.netty.provider.listener.NettyProviderBindListener;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @description: 提供者
 * @date: 2021/12/23 22:38
 */
public class NettyProvider {

  private Bootstrap bootstrap;

  /**
   * NettyLoggerInfoAdapter.getLogLevel()
   * @param initializer
   */
  public void provide(NettyChannelInitializer initializer, LogLevel level){
    NioEventLoopGroup worker = new NioEventLoopGroup(1);
    bootstrap = new Bootstrap();
    bootstrap.group(worker).channel(NioSocketChannel.class)
        .handler(new LoggingHandler(level))
        .handler(initializer);
  }

  public void operate(){
    bootstrap.option(ChannelOption.SO_BACKLOG,1024);
  }

  public void bind(String host, int port) throws NetworkException {
    try {
      bootstrap.bind(host,port).sync().addListener(new NettyProviderBindListener());
    } catch (Exception e) {
      throw new NetworkException(NetworkExceptionEnum.CLIENT_BIND_ERR,e);
    }

  }


}