package com.github.zhenwei.network.netty.nio.client;


import com.github.zhenwei.network.netty.nio.client.future.ClienMessageFuture;
import com.github.zhenwei.network.netty.nio.client.future.ClientFutureHolder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import java.util.concurrent.TimeUnit;

public class NettyClient {

  private ChannelFuture future;
  private ClienMessageFuture messageFuture;
  public static final String FUTURE_KEY = "i'm demo key";

  public NettyClient build(String ip, int port) throws Exception {
    Bootstrap client = new Bootstrap().group(new NioEventLoopGroup())
        .channel(NioSocketChannel.class)
        .option(ChannelOption.SO_KEEPALIVE, true)
        .handler(
            new ChannelInitializer<NioSocketChannel>() {
              @Override
              protected void initChannel(NioSocketChannel channel) throws Exception {
                channel.pipeline()
                    .addLast(new LoggingHandler(LogLevel.TRACE),
                        //输出 处理
                        new ClientMessageEncoderHandler(),
                        //输入 处理
                        new ClientMessageDecoderHandler()
                    );


              }
            });
    future = client.connect(ip, port).addListener(future -> {
      if (future.isSuccess()) {
        System.out.println("客户端启动");
      } else {
        System.out.println("客户端链接失败,断开链接");
        close();
      }
    }).sync();
    return this;
  }

  public void close() {
    if (future != null) {
      try {
        future.channel().closeFuture().get(2, TimeUnit.SECONDS);
      } catch (Exception ignore) {
      } finally {
        future = null;
      }
    }
  }

  public NettyClient sendMessage(String message) throws InterruptedException {
    future.channel().writeAndFlush(message);
    System.out.println("客户端发送数据：" + message);
    messageFuture = new ClienMessageFuture();
    ClientFutureHolder.add(FUTURE_KEY, messageFuture);
    return this;
  }

  //Future
  public String readMessage() {
    String message = "empty";
    if (messageFuture != null) {
      try {
        message = messageFuture.get(10, TimeUnit.SECONDS);
      } catch (Exception e) {
        System.out.println("messageFuture get message err");
        e.printStackTrace();
      }
    }
    return message;
  }

}