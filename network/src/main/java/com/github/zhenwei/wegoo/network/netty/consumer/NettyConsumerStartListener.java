package com.github.zhenwei.wegoo.network.netty.consumer;

import io.netty.channel.DefaultChannelPromise;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * future实现类为: {@link io.netty.bootstrap.AbstractBootstrap.PendingRegistrationPromise}
 */
public class NettyConsumerStartListener implements GenericFutureListener<DefaultChannelPromise> {

  private static final InternalLogger logger = InternalLoggerFactory.getInstance(
      NettyConsumerStartListener.class);

  @Override
  public void operationComplete(DefaultChannelPromise future) throws Exception {
    if (future.isSuccess()) {
      logger.debug("server start success");
    } else {
      logger.error("server start fail, system will exit");
      System.exit(0);
    }

  }

}