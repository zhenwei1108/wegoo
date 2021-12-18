package com.github.zhenwei.network.netty.nio.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ServerMessageDecoderAsyncHandler extends ByteToMessageDecoder {

  //接受消息处理
  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

    /**
     * 异步执行
     */
    ctx.channel().eventLoop().execute(() -> ctx.write("runnable "));

    /**
     * 异步延时执行
     *  scheduleTaskQueue
     */
    ctx.channel().eventLoop().schedule(() -> {
    //可读长度, 默认 1024字节后被分包
      int readableLength = in.readableBytes();
      byte[] data = new byte[readableLength];
      in.readBytes(data);
      String message = new String(data, StandardCharsets.UTF_8);
      out.add(message);
      ctx.writeAndFlush("i have received your message: " + message);
      System.out.println("服务端收到并应答消息：" + message);

    }, 1, TimeUnit.SECONDS);

  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    System.out.println("服务端处理错误");
    cause.printStackTrace();
    ctx.close();
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    System.out.println("消息读取完毕后,执行此方法");
  }
}