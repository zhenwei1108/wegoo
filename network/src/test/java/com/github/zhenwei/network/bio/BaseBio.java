package com.github.zhenwei.network.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class BaseBio {

  InputStream inputStream = null;
  OutputStream outputStream = null;
  Socket socket = null;


  /**
   * 读消息
   *
   * @return
   * @throws Exception
   */
  public byte[] readMessage() throws Exception {
    byte[] data = null;
    if (this.inputStream != null) {
      try {
        int available;
        do {
          //todo 发送完消息,单纯读input容易获取不到消息.
          available = inputStream.available();
          data = new byte[available];
          int read = inputStream.read(data);
          System.out.println("读取数据长度:" + read);
        } while (available == 0);
      } catch (IOException e) {
        throw new Exception("bio client: read message error", e);
      }
    }
    return data;
  }


  /**
   * 写消息
   *
   * @param data
   * @throws Exception
   */
  public void sendMessage(byte[] data) throws Exception {
    if (outputStream != null) {
      try {
        outputStream.write(data);
        outputStream.flush();
      } catch (IOException e) {
        throw new Exception("bio client: send message error", e);
      }
    }

  }

  public void setSocket(Socket socket) throws IOException {
    this.socket = socket;
    inputStream = socket.getInputStream();
    outputStream = socket.getOutputStream();
  }

  /**
   * 关闭链接, 置空后等待回收
   */
  public void closeConnect() {
    try {
      if (inputStream != null) {
        inputStream.close();
      }
      if (outputStream != null) {
        outputStream.close();
      }
      if (socket != null) {
        socket.close();
      }
    } catch (IOException ignore) {
    } finally {
      inputStream = null;
      outputStream = null;
      socket = null;
    }

  }

}