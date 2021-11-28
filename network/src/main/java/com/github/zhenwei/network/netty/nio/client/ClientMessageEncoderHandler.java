package com.github.zhenwei.network.netty.nio.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.nio.charset.StandardCharsets;

public class ClientMessageEncoderHandler extends MessageToByteEncoder<String> {

  @Override
  protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
    out.writeBytes(msg.getBytes(StandardCharsets.UTF_8));
  }
}