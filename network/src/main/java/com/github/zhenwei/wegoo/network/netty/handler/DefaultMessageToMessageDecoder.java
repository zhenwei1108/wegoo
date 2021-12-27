package com.github.zhenwei.wegoo.network.netty.handler;

import com.github.zhenwei.wegoo.network.eneity.BaseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;

public class DefaultMessageToMessageDecoder<T extends BaseMessage> extends
    MessageToMessageDecoder<T> implements AbstractDecoder<T> {

  @Override
  public void decode(ChannelHandlerContext ctx, T in, List<Object> out) throws Exception {

  }
}