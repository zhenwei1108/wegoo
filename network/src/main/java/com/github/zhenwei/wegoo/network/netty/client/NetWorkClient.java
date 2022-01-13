package com.github.zhenwei.wegoo.network.netty.client;

import com.github.zhenwei.wegoo.common.exception.NetworkException;
import com.github.zhenwei.wegoo.network.netty.BaseNetty;
import com.github.zhenwei.wegoo.network.netty.NettyChannelInitializer;
import com.github.zhenwei.wegoo.network.netty.consumer.NettyLoggerInfoAdapter;
import com.github.zhenwei.wegoo.network.netty.listerner.NettyProviderBindListener;
import com.github.zhenwei.wegoo.network.netty.provider.DefaultNettyProvider;
import com.github.zhenwei.wegoo.network.netty.provider.DefaultProviderChannelInitializer;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.handler.logging.LogLevel;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * 客户端
 */
public class NetWorkClient<T> implements BaseNetty<T> {

  private final DefaultNettyProvider nettyProvider = new DefaultNettyProvider();
  private ChannelFuture future;

  /**
   * @param [host, port, initializer, listener, level] ip, port, 初始化实现, 端口绑定监听, 日志级别
   * @return void
   * @author zhangzhenwei
   * @description 客户端构造
   * @date 2021/12/26 21:40
   */
  public NetWorkClient(String host, int port, NettyChannelInitializer initializer,
      GenericFutureListener<ChannelPromise> listener, LogLevel level) throws NetworkException {
    if (initializer == null) {
      initializer = new DefaultProviderChannelInitializer();
    }
    future = nettyProvider.provide(host, port, initializer, listener, level);
  }

  public NetWorkClient(String host, int port) throws NetworkException {
    this(host, port, new DefaultProviderChannelInitializer(),
        new NettyProviderBindListener<>(), NettyLoggerInfoAdapter.getLogLevel());
  }

  public NetWorkClient(String host, int port, NettyChannelInitializer initializer)
      throws NetworkException {
    this(host, port, initializer, new NettyProviderBindListener<>(),
        NettyLoggerInfoAdapter.getLogLevel());
  }


  @Override
  public void send(T t) {
    future.channel().writeAndFlush(t);
  }

  @Override
  public void close() {
    future.channel().close();
  }
}