package com.github.zhenwei.wegoo.network.netty.consumer.listerner;

import com.github.zhenwei.wegoo.network.netty.consumer.BaseOperation;
import io.netty.channel.DefaultChannelPromise;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Vector;

/**
 * future实现类为: {@link io.netty.bootstrap.AbstractBootstrap.PendingRegistrationPromise}
 */
public class NettyConsumerStartListener implements GenericFutureListener<DefaultChannelPromise> {

  private static final InternalLogger logger = InternalLoggerFactory.getInstance(
      NettyConsumerStartListener.class);

  private final Vector<BaseOperation> successOperations = new Vector<>(1);
  private final Vector<BaseOperation> failOperations = new Vector<>(1);


  @Override
  public void operationComplete(DefaultChannelPromise future) throws Exception {
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
  public void serverStartSuccess() {
    successOperations.forEach(BaseOperation::operate);
  }

  public void serverStartFail() {
    failOperations.forEach(BaseOperation::operate);
  }

  public NettyConsumerStartListener setSuccessOperations(
      BaseOperation successOperation) {
    this.successOperations.add(successOperation);
    return this;
  }

  public NettyConsumerStartListener setFailOperations(
      BaseOperation failOperation) {
    this.failOperations.add(failOperation);
    return this;
  }

}