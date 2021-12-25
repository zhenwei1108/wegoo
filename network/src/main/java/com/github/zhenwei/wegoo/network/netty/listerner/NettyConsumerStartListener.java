package com.github.zhenwei.wegoo.network.netty.listerner;

import com.github.zhenwei.wegoo.network.netty.consumer.BaseOperation;
import io.netty.channel.DefaultChannelPromise;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Vector;

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