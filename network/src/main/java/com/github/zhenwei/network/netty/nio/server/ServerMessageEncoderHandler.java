package com.github.zhenwei.network.netty.nio.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

public class ServerMessageEncoderHandler extends MessageToByteEncoder {

  @Override
  protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
    out.writeBytes(msg.toString().getBytes(StandardCharsets.UTF_8));
  }
}