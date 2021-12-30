package com.github.zhenwei.wegoo.network.netty.handler;

import com.github.zhenwei.wegoo.network.entity.BaseMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;

public interface AbstractEncoder<T> extends ChannelOutboundHandler {

  void encode(ChannelHandlerContext ctx, BaseMessage msg, ByteBuf out) throws Exception ;
}