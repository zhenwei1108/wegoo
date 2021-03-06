package com.github.zhenwei.wegoo.network.netty.handler.decoder;

import com.github.zhenwei.wegoo.network.netty.entity.BaseMessage;
import com.github.zhenwei.wegoo.network.netty.handler.AbstractBaseMessageDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;

@Sharable
public class DefaultMessageToMessageDecoder<T extends BaseMessage> extends
    AbstractBaseMessageDecoder<BaseMessage> {


  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

  }
}