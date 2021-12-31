package com.github.zhenwei.wegoo.network.netty.handler;

import com.github.zhenwei.wegoo.network.entity.BaseMessage;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
@Sharable
public class DefaultMessageToMessageDecoder<T extends BaseMessage> extends
    MessageToMessageDecoder<BaseMessage> implements AbstractDecoder<BaseMessage> {

  @Override
  public void decode(ChannelHandlerContext ctx, BaseMessage msg, List<Object> out)
      throws Exception {
    System.out.println(msg);
  }
}