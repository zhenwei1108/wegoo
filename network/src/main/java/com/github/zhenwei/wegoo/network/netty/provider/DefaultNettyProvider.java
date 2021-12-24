package com.github.zhenwei.wegoo.network.netty.provider;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;

/**
 * @description: Provider默认实现
 * @author: zhangzhenwei
 * @date: 2021/12/24 23:46
 */
public class DefaultNettyProvider extends NettyProvider {

  @Override
  public void option(Bootstrap bootstrap) {
    bootstrap.option(ChannelOption.SO_KEEPALIVE, true)
        .option(ChannelOption.AUTO_CLOSE, true);
  }
}