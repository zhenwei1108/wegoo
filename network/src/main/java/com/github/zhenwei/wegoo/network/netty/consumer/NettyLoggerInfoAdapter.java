package com.github.zhenwei.wegoo.network.netty.consumer;

import io.netty.handler.logging.LogLevel;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 *
 */
public class NettyLoggerInfoAdapter {

  private static final InternalLogger logger = InternalLoggerFactory.getInstance(
      NettyConsumerStartListener.class);
  private static LogLevel level = LogLevel.WARN;


  public static LogLevel getLogLevel() {
    if (logger.isTraceEnabled()) {
      level = LogLevel.TRACE;
    }
    if (logger.isDebugEnabled()) {
      level = LogLevel.DEBUG;
    }
    return level;
  }

  public static void setLevel(LogLevel level) {
    NettyLoggerInfoAdapter.level = level;
  }
}