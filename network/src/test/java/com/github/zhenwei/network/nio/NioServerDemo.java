package com.github.zhenwei.network.nio;

import com.github.zhenwei.network.io.nio.NioServer;
import org.junit.Test;

public class NioServerDemo {

  @Test
  public void server() throws Exception {
    NioServer server = new NioServer();
    server.server(10086);
  }



}