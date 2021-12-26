package com.github.zhenwei.wegoo.network.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
/**
 * @description: 入站使用, 用于消息解码,将byte[] 转为 执行对象
 * @author: zhangzhenwei
 * @date: 2021/12/26 21:53
 */
public class DefaultByteToMessageDecoder extends ByteToMessageDecoder {

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    
  }
}