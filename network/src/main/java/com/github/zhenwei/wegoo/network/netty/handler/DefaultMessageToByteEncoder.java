package com.github.zhenwei.wegoo.network.netty.handler;

import com.github.zhenwei.wegoo.network.entity.BaseMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @description: 消息出站使用, 将指定对象编码为 byte[]
 * @author: zhangzhenwei
 * @date: 2021/12/26 21:54
 */
@Sharable
public class DefaultMessageToByteEncoder extends MessageToByteEncoder<BaseMessage> implements AbstractEncoder<BaseMessage>{

  //todo 参考redis 填入序列化方法.
  @Override
  public void encode(ChannelHandlerContext ctx, BaseMessage msg, ByteBuf out) throws Exception {
    byte[] message = msg.getEncode();
    out.writeInt(message.length);
    out.writeBytes(message);
    System.out.println(msg);
  }

}