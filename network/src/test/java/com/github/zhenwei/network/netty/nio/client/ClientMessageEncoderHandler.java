package com.github.zhenwei.network.netty.nio.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.nio.charset.StandardCharsets;

public class ClientMessageEncoderHandler extends MessageToByteEncoder<String> {

  @Override
  protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
    byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
    byte[] data = new byte[2048];
    System.arraycopy(bytes, 0, data, 0, Math.min(bytes.length, data.length));
    out.writeInt(data.length);
    out.writeBytes(data);
//    Persion persion = Persion.newBuilder().setName("zhangsan").setAddr("tiananmen").build();
//    ctx.writeAndFlush(msg);
  }
}