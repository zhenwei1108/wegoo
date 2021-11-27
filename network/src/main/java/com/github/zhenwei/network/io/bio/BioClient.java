package com.github.zhenwei.network.io.bio;

import java.io.IOException;
import java.net.Socket;
import lombok.Setter;

/**
 * @author zhenwei BIO 又称 OIO (old io ?)
 */
@Setter
public class BioClient extends BaseBio{


  public static BioClient build(String ip, int port) throws Exception {
    BioClient bioClient = new BioClient();
    try {
      Socket clientSocket = new Socket(ip, port);
      bioClient.setSocket(clientSocket);
    } catch (IOException e) {
      throw new Exception(String.format("bio client: connect %s : %s error", ip, port), e);
    }
    return bioClient;
  }



}