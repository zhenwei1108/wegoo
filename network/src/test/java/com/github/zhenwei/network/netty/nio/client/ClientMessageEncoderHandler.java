package com.github.zhenwei.network.netty.nio.client;

import com.github.zhenwei.network.netty.nio.proto.PersionEntity.Persion;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ClientMessageEncoderHandler extends MessageToByteEncoder<String> {

  @Override
  protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
//    out.writeBytes(msg.getBytes(StandardCharsets.UTF_8));
    Persion persion = Persion.newBuilder().setName("zhangsan").setAddr("tiananmen").build();
    ctx.writeAndFlush(persion);
  }
}