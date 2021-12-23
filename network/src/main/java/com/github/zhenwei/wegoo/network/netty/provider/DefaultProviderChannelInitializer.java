package com.github.zhenwei.wegoo.network.netty.provider;

import com.github.zhenwei.wegoo.network.netty.NettyChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class DefaultProviderChannelInitializer extends NettyChannelInitializer {

  @Override
  protected void init(SocketChannel channel) throws Exception {
    //do nothing
  }
}