package com.github.zhenwei.wegoo.network.netty.provider;

import com.github.zhenwei.wegoo.network.netty.NettyChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @description: default impl for ChannelInitializer
 * @see  io.netty.channel.ChannelInitializer
 * @author: zhangzhenwei
 * @date: 2021/12/26 21:42
 */
public class DefaultProviderChannelInitializer extends NettyChannelInitializer {

  @Override
  protected void init(SocketChannel channel) throws Exception {
    //do nothing
  }
}