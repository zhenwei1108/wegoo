package com.github.zhenwei.wegoo.network.server;

import com.github.zhenwei.wegoo.common.exception.NetworkException;
import com.github.zhenwei.wegoo.network.entity.ObjectMessage;
import com.github.zhenwei.wegoo.network.netty.NettyChannelInitializer;
import com.github.zhenwei.wegoo.network.netty.consumer.DefaultConsumerChannelInitializer;
import com.github.zhenwei.wegoo.network.netty.consumer.DefaultNettyConsumer;
import com.github.zhenwei.wegoo.network.netty.consumer.NettyLoggerInfoAdapter;
import com.github.zhenwei.wegoo.network.netty.handler.decoder.DefaultByteToMessageDecoder;
import com.github.zhenwei.wegoo.network.netty.handler.encoder.DefaultChannelInboundHandler;
import com.github.zhenwei.wegoo.network.netty.handler.decoder.DefaultMessageToMessageDecoder;
import com.github.zhenwei.wegoo.network.netty.listerner.NettyConsumerStartListener;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelPromise;
import io.netty.handler.logging.LogLevel;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.ArrayList;

/**
 * 服务端
 */
public class NetWorkServer {

  private DefaultNettyConsumer defaultConsumer = new DefaultNettyConsumer();

  public void server(int workerSize, int port, NettyChannelInitializer initializer,
      GenericFutureListener<ChannelPromise> listener, LogLevel level)
      throws NetworkException {
    defaultConsumer.consume(workerSize, port, initializer, listener, level);
  }

  public void server(int port, ArrayList<String> whiteLists, ChannelInboundHandler... handlers)
      throws NetworkException {
    //增加白名单
    DefaultConsumerChannelInitializer initialize = new DefaultConsumerChannelInitializer()
        .predicate(clientHosts -> {
          if (whiteLists == null || whiteLists.size() == 0) {
            return false;
          }
          return !whiteLists.contains(clientHosts);
        }).addHandler(handlers);
    NettyConsumerStartListener<ChannelPromise> listener = new NettyConsumerStartListener<>();
    LogLevel level = NettyLoggerInfoAdapter.getLogLevel();
    server(0, port, initialize, listener, level);
  }

  public void server(int port) throws NetworkException {
    server(port, null, new DefaultByteToMessageDecoder(),
        new DefaultMessageToMessageDecoder<ObjectMessage>(),
        new DefaultChannelInboundHandler());
  }

}