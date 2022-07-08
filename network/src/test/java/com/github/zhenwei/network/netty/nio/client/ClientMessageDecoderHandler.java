package com.github.zhenwei.network.netty.nio.client;

import com.github.zhenwei.network.netty.nio.client.future.ClientFutureHolder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ClientMessageDecoderHandler extends ByteToMessageDecoder {

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    int readLen = in.readableBytes();
    if (readLen > 0) {
      System.out.println("i'm decoder:" + readLen);
      byte[] data = new byte[readLen];
      in.readBytes(data);
      ClientFutureHolder.success(NettyClient.FUTURE_KEY, new String(data, StandardCharsets.UTF_8));
    }
  }


  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//    System.out.println("客户端异常");
//    cause.printStackTrace();
    ClientFutureHolder.fail(NettyClient.FUTURE_KEY, cause);
    System.out.println("关闭链接");
    ctx.close();
  }



}