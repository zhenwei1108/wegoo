package com.github.zhenwei.wegoo.network.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public abstract class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel channel) throws Exception {
    init(channel.pipeline());
  }

  protected abstract void init(ChannelPipeline channel) throws Exception;

}