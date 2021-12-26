package com.github.zhenwei.wegoo.network.server;

import com.github.zhenwei.wegoo.common.exception.NetworkException;
import com.github.zhenwei.wegoo.network.netty.NettyChannelInitializer;
import com.github.zhenwei.wegoo.network.netty.consumer.DefaultConsumerChannelInitialzer;
import com.github.zhenwei.wegoo.network.netty.consumer.DefaultNettyConsumer;
import com.github.zhenwei.wegoo.network.netty.consumer.NettyLoggerInfoAdapter;
import com.github.zhenwei.wegoo.network.netty.listerner.NettyConsumerStartListener;
import io.netty.channel.ChannelPromise;
import io.netty.handler.logging.LogLevel;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * 服务端
 */
public class NetWorkServer {

  private DefaultNettyConsumer defaultConsumer = new DefaultNettyConsumer();

  public void server(int workerSize, int port, NettyChannelInitializer initializer,  GenericFutureListener<ChannelPromise> listener, LogLevel level) throws NetworkException {
    defaultConsumer.consume(workerSize, port, initializer,listener,level);
  }


  public void server( int port) throws NetworkException {
    DefaultConsumerChannelInitialzer initialize = new DefaultConsumerChannelInitialzer();
    NettyConsumerStartListener<ChannelPromise> listener = new NettyConsumerStartListener<>();
    LogLevel level = NettyLoggerInfoAdapter.getLogLevel();
    defaultConsumer.consume(0, port, initialize,listener,level);
  }

  public static void main(String[] args) throws NetworkException {
    NetWorkServer netWorkServer = new NetWorkServer();
    netWorkServer.server(10980);
  }
}