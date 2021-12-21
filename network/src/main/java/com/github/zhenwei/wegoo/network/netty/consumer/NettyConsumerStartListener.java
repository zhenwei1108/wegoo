package com.github.zhenwei.wegoo.network.netty.consumer;

import io.netty.channel.DefaultChannelPromise;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Arrays;

/**
 * future实现类为: {@link io.netty.bootstrap.AbstractBootstrap.PendingRegistrationPromise}
 */
public class NettyConsumerStartListener implements GenericFutureListener<DefaultChannelPromise> {

  private static final InternalLogger logger = InternalLoggerFactory.getInstance(
      NettyConsumerStartListener.class);

  @Override
  public void operationComplete(DefaultChannelPromise future) throws Exception {
    if (future.isSuccess()) {
      serverStartSuccess();
    } else {
      serverStartFail();
    }
  }



  /**
   * you can override this method
   */
  public void serverStartSuccess(BaseOperation... operate) {
    if (operate != null) {
      Arrays.stream(operate).forEach(BaseOperation::operate);
    }
  }

  public void serverStartFail(BaseOperation... operate) {
    if (operate != null) {
      Arrays.stream(operate).forEach(BaseOperation::operate);
    }
  }

  public static void main(String[] args) {
    NettyConsumerStartListener listener = new NettyConsumerStartListener();
    listener.serverStartSuccess(null);
  }

}