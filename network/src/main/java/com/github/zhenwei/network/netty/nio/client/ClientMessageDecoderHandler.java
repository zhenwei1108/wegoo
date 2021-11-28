package com.github.zhenwei.network.netty.nio.client;

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
      System.out.println("i'm decoder");
      byte[] data = new byte[readLen];
      in.readBytes(data);
      out.add(new String(data, StandardCharsets.UTF_8));
    }

  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("client 收到消息:" + msg);

  }
}