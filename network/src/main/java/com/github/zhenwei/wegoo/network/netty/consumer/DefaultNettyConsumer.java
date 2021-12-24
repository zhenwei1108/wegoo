package com.github.zhenwei.wegoo.network.netty.consumer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;

/**
 * @description: NettyConsumer默认实现
 * @author: zhangzhenwei
 * @date: 2021/12/24 23:45
 */
public class DefaultNettyConsumer extends NettyConsumer {

  @Override
  public void bossOption(ServerBootstrap serverBootstrap) {

  }

  @Override
  public void workerOption(ServerBootstrap serverBootstrap) {
    serverBootstrap.option(ChannelOption.AUTO_CLOSE, true);

  }
}