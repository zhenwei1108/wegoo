package com.github.zhenwei.wegoo.network.server;

import com.github.zhenwei.wegoo.common.exception.NetworkException;
import com.github.zhenwei.wegoo.network.netty.consumer.NettyConsumer;
import io.netty.channel.ChannelOption;

/**
 * 服务端
 */
public class NetWorkServer {

  public static void server(int port) throws NetworkException {
    NettyConsumer.getInstance().server()
        .option(ChannelOption.AUTO_CLOSE, true)
        .option(ChannelOption.SO_BACKLOG, 1024)
        .run(port);
  }


  public static void main(String[] args) throws NetworkException {
    server(10980);
  }

}