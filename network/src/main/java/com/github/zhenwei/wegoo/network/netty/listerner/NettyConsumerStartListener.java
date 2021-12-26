package com.github.zhenwei.wegoo.network.netty.listerner;

import io.netty.util.concurrent.Future;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * future实现类为: {@link io.netty.bootstrap.AbstractBootstrap.PendingRegistrationPromise}
 */
public class NettyConsumerStartListener<F extends Future<?>> extends AbstractGenericFutureListener<F>{

  private static final InternalLogger logger = InternalLoggerFactory.getInstance(
      NettyConsumerStartListener.class);

  @Override
  public void startSuccess() {
    logger.info("server start success");
  }

  @Override
  public void startFail() {
    logger.error("server start fail");
  }

}