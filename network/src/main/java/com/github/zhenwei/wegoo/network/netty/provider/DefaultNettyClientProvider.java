package com.github.zhenwei.wegoo.network.netty.provider;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelOption;

/**
 * @description: Provider默认实现
 * @author: zhangzhenwei
 * @date: 2021/12/24 23:46
 */
public class DefaultNettyClientProvider extends NettyProvider {

  @Override
  public void option(Bootstrap bootstrap) {
    bootstrap
        .option(ChannelOption.SO_KEEPALIVE, true)
        .option(ChannelOption.SO_TIMEOUT,2000)
        .option(ChannelOption.AUTO_CLOSE, true)
        //发送缓冲区大小, 内核参数: net.core.wmem_max
        .option(ChannelOption.SO_SNDBUF,1024)
        //禁用 Nagle算法
        .option(ChannelOption.TCP_NODELAY,false)
        //从缓冲区读取数据大小,自适应调整
        .option(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator())

        ;
  }
}