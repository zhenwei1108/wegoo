package com.github.zhenwei.network.bio;

import com.github.zhenwei.network.io.bio.BioClient;
import com.github.zhenwei.network.io.bio.BioServer;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

public class BioDemoTest {

  int serverPort = 10080;

  @Test
  public void bioClient() throws Exception {
    String data = "hello word";
    BioClient bioClient = BioClient.build("localhost", serverPort);
    bioClient.sendMessage(data.getBytes(StandardCharsets.UTF_8));
    System.out.println("bio client 发送消息: " + data);

    byte[] result = bioClient.readMessage();
    System.out.println("bio client 接收到消息为:" + new String(result, StandardCharsets.UTF_8));
    bioClient.closeConnect();
  }

  @Test
  public void bioServer(){
    BioServer.server(serverPort);
  }



}