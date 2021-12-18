package com.github.zhenwei.network.bio;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BioServer implements Runnable {

  /**
   * 服务端口号
   */
  int port = 0;
  /**
   * 工作线程
   */
  static final ThreadPoolExecutor workerThreadPool = new ThreadPoolExecutor(1,
      Math.max(1, Runtime.getRuntime().availableProcessors() >> 1), 10,
      TimeUnit.SECONDS, new LinkedBlockingQueue<>(200),
      new ThreadPoolExecutor.CallerRunsPolicy());
  /**
   * 服务端线程池
   */
  static final ThreadPoolExecutor bossThreadPool = new ThreadPoolExecutor(1, 1, 1,
      TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));

  static final BioServer SERVER = new BioServer();

  public static void server(int port) {
    bossThreadPool.execute(SERVER.setPort(port));
    while (!bossThreadPool.isShutdown()) {
    }
  }

  @Override
  public void run() {
    try {
      //启动服务
      ServerSocket serverSocket = new ServerSocket(port);
      System.out.println("服务端启动, port:" + port);
      for (; ; ) {
        //接收链接
        Socket socket = serverSocket.accept();
        //异步处理
        BioServerHandler bioServerHandler = new BioServerHandler();
        bioServerHandler.setSocket(socket);
        workerThreadPool.execute(bioServerHandler);
      }

    } catch (Exception e) {
      System.out.printf("create bio server port %s error", port);
      e.printStackTrace();
      System.exit(0);
    }

  }

  /**
   * 设置端口号
   *
   * @param port
   * @return
   */
  public BioServer setPort(int port) {
    if (this.port == 0) {
      this.port = port;
    }
    return this;
  }

  static class BioServerHandler extends BaseBio implements Runnable {

    @Override
    public void run() {
      try {
        byte[] data = readMessage();
        System.out.println("bio server 接收到消息为:" + new String(data, StandardCharsets.UTF_8));
        sendMessage("i had received your message".getBytes(StandardCharsets.UTF_8));
        System.out.println("bio server 已应答");
      } catch (Exception e) {
        System.out.println("bio server receive message err");
        e.printStackTrace();
      }

    }
  }


}