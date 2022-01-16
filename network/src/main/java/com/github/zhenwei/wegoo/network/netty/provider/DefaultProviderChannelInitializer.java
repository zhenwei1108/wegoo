package com.github.zhenwei.wegoo.network.netty.provider;

import com.github.zhenwei.wegoo.network.netty.NettyChannelInitializer;
import com.github.zhenwei.wegoo.network.netty.handler.encoder.DefaultMessageToByteEncoder;
import io.netty.channel.ChannelPipeline;

/**
 * @description: default impl for ChannelInitializer
 * @see  io.netty.channel.ChannelInitializer
 * @author: zhangzhenwei
 * @date: 2021/12/26 21:42
 */
public class DefaultProviderChannelInitializer extends NettyChannelInitializer {

  @Override
  protected void init(ChannelPipeline pipeline) throws Exception {
    pipeline.addLast(new DefaultMessageToByteEncoder());
    //do nothing
  }
}