package com.github.zhenwei.wegoo.network.client;

import com.github.zhenwei.wegoo.common.exception.NetworkException;
import com.github.zhenwei.wegoo.network.netty.NettyChannelInitializer;
import com.github.zhenwei.wegoo.network.netty.consumer.NettyLoggerInfoAdapter;
import com.github.zhenwei.wegoo.network.netty.listerner.NettyProviderBindListener;
import com.github.zhenwei.wegoo.network.netty.provider.DefaultNettyProvider;
import com.github.zhenwei.wegoo.network.netty.provider.DefaultProviderChannelInitializer;
import com.github.zhenwei.wegoo.network.serialize.DefaultProtobufSerializer;
import com.github.zhenwei.wegoo.network.serialize.NettySerializer;
import io.netty.channel.ChannelPromise;
import io.netty.handler.logging.LogLevel;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * 客户端
 */
public class NetWorkClient {

  private DefaultNettyProvider nettyProvider = new DefaultNettyProvider();

  /**
   * @param [host, port, initializer, listener, level] ip, port, 初始化实现, 端口绑定监听, 日志级别
   * @return void
   * @author zhangzhenwei
   * @description 客户端构造
   * @date 2021/12/26 21:40
   */
  public void client(String host, int port, NettyChannelInitializer initializer,
      GenericFutureListener<ChannelPromise> listener, NettySerializer serializer,
      LogLevel level) throws NetworkException {
    //todo 序列化实现方式
    nettyProvider.provide(host, port, initializer, listener, level);
  }

  public void client(String host, int port) throws NetworkException {
    DefaultProviderChannelInitializer initializer = new DefaultProviderChannelInitializer();
    NettyProviderBindListener<ChannelPromise> listener = new NettyProviderBindListener<>();
    LogLevel level = NettyLoggerInfoAdapter.getLogLevel();
    DefaultProtobufSerializer serializeHandler = new DefaultProtobufSerializer(null);
    client(host, port, initializer, listener, serializeHandler, level);
  }

  public void client(String host, int port, NettyChannelInitializer initializer)
      throws NetworkException {
    NettyProviderBindListener<ChannelPromise> listener = new NettyProviderBindListener<>();
    LogLevel level = NettyLoggerInfoAdapter.getLogLevel();
    DefaultProtobufSerializer serializeHandler = new DefaultProtobufSerializer(null);
    client(host, port, initializer, listener, serializeHandler, level);
  }


}