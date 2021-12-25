package com.github.zhenwei.wegoo.network.netty.consumer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelOption;

/**
 * @description: NettyConsumer默认实现
 * @author: zhangzhenwei
 * @date: 2021/12/24 23:45
 */
public class DefaultNettyConsumer extends NettyConsumer {

  @Override
  public void option(ServerBootstrap serverBootstrap) {
    serverBootstrap.option(ChannelOption.AUTO_CLOSE, true)
        .option(ChannelOption.SO_BACKLOG, 2048)
        .option(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator())
        .option(ChannelOption.SO_RCVBUF, 2048)
    ;
  }

  @Override
  public void childOption(ServerBootstrap serverBootstrap) {
    serverBootstrap
        .childOption(ChannelOption.AUTO_CLOSE, true)
        .childOption(ChannelOption.SO_TIMEOUT, 2000)
        .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)

    ;

  }
}