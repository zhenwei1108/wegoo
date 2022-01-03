package com.github.zhenwei.wegoo.network.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import java.util.List;

/**
 * @description: 入站使用, 用于消息解码,将byte[] 转为 执行对象
 * @author: zhangzhenwei
 * @date: 2021/12/26 21:56
 * @see io.netty.handler.codec.ReplayingDecoder 重写了callDecode 方法,调用其中decodeRemovalReentryProtection()
 * 方法
 */
@Sharable
public class DefaultByteToMessageDecoder extends ReplayingDecoder<Void> implements
    AbstractDecoder<ByteBuf> {

  @Override
  public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    //todo 如何正确读取传入数据
    int bodyLen = in.readInt();
    byte[] body = new byte[bodyLen];
    in.readBytes(body);
    System.out.println(in);
  }
}