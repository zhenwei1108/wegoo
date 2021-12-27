package com.github.zhenwei.wegoo.network.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import java.util.List;

public interface AbstractDecoder<T> extends ChannelInboundHandler {

  void decode(ChannelHandlerContext ctx, T in, List<Object> out) throws Exception;
}