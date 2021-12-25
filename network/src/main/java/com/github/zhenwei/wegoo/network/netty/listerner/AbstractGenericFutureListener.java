package com.github.zhenwei.wegoo.network.netty.listerner;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public abstract class AbstractGenericFutureListener<F extends Future<?>> implements
    GenericFutureListener<F> {

  private static final InternalLogger logger = InternalLoggerFactory.getInstance(
      NettyConsumerStartListener.class);

  @Override
  public void operationComplete(F future) throws Exception {
    if (future.isSuccess()) {
      logger.info("server start success");
      serverStartSuccess();
    } else {
      logger.error("server start fail");
      serverStartFail();
    }
  }

  /**
   * you can override this method
   */
  public abstract void serverStartSuccess();

  public abstract void serverStartFail();

}