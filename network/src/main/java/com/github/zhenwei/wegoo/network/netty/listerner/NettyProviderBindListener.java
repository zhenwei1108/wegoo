package com.github.zhenwei.wegoo.network.netty.listerner;

import io.netty.channel.DefaultChannelPromise;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * @description: 绑定端口监听
 * @date: 2021/12/23 22:37
 */
public class NettyProviderBindListener extends AbstractGenericFutureListener<DefaultChannelPromise>{

  private static final InternalLogger logger = InternalLoggerFactory.getInstance(
      NettyProviderBindListener.class);

  @Override
  public void startSuccess() {
    logger.info("client bind port success");
  }

  @Override
  public void startFail() {
    logger.info("client bind port fail");
  }
}