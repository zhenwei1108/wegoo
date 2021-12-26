package com.github.zhenwei.wegoo.network.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @description: 消息出站使用, 将指定对象编码为 byte[]
 * @author: zhangzhenwei
 * @date: 2021/12/26 21:54
 */
public class DefaultMessageToByteEncoder extends MessageToByteEncoder {

  @Override
  protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

  }
}