package com.github.zhenwei.wegoo.network.netty.listerner;

import io.netty.channel.DefaultChannelPromise;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * future实现类为: {@link io.netty.bootstrap.AbstractBootstrap.PendingRegistrationPromise}
 */
public class NettyConsumerStartListener extends AbstractGenericFutureListener<DefaultChannelPromise>{

  private static final InternalLogger logger = InternalLoggerFactory.getInstance(
      NettyConsumerStartListener.class);

  @Override
  public void serverStartSuccess() {

  }

  @Override
  public void serverStartFail() {

  }

}