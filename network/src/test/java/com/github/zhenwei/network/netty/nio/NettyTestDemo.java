package com.github.zhenwei.network.netty.nio;

import com.github.zhenwei.network.netty.nio.client.NettyClient;
import com.github.zhenwei.network.netty.nio.server.NettyServer;
import org.junit.Test;

public class NettyTestDemo {

  int port = 10980;

  @Test
  public void server() throws InterruptedException {
    NettyServer nettyServer = new NettyServer();
    nettyServer.server(port);
  }


  @Test
  public void client() throws Exception {
    NettyClient nettyClient = new NettyClient();
    StringBuffer data = new StringBuffer("hello tony ");
    for (int i = 0; i < 10; i++) {
      data.append(data);
    }
    String message = nettyClient.build("localhost", port).sendMessage(data.toString()).readMessage();
    System.out.println("客户端收到应答:" + message);
    nettyClient.close();

  }


}