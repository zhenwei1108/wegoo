package com.github.zhenwei.network.consumer;

import com.github.zhenwei.wegoo.common.exception.NetworkException;
import com.github.zhenwei.wegoo.network.netty.consumer.NettyConsumer;
import org.junit.Test;

public class ConsumerTest {

  @Test
  public void server() throws NetworkException {
    NettyConsumer consumer = new NettyConsumer();
//  consumer.server();
    consumer.run(19090);
  }

}