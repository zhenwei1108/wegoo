package com.github.zhenwei.network.netty.nio.client;


import com.github.zhenwei.network.netty.nio.client.future.ClienMessageFuture;
import com.github.zhenwei.network.netty.nio.client.future.ClientFutureHolder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
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
    //客户端尽量设置为1,防止句柄占用
    NioEventLoopGroup group = new NioEventLoopGroup(1);
    Bootstrap client = new Bootstrap().group(group)
        .channel(NioSocketChannel.class)
        .handler(new LoggingHandler(LogLevel.DEBUG))
//        .option(ChannelOption.SO_KEEPALIVE, true)
//        .option(ChannelOption.AUTO_CLOSE, true)
//        //发送缓冲区大小, 内核参数: net.core.wmem_max
//        .option(ChannelOption.SO_SNDBUF,1024)
//        //禁用 Nagle算法
//        .option(ChannelOption.TCP_NODELAY,false)
//        //从缓冲区读取数据大小,自适应调整
//        .option(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator())
        .handler(
            new ChannelInitializer<NioSocketChannel>() {
              @Override
              protected void initChannel(NioSocketChannel channel) throws Exception {
                channel.pipeline()
                    .addLast(
                        //使用protobuf传输对象
//                        new ProtobufEncoder(),
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
    System.out.println("发送数据长度"+message.length());
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