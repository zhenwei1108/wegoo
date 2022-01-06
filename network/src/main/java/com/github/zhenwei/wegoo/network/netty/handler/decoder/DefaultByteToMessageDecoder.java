package com.github.zhenwei.wegoo.network.netty.handler.decoder;

import com.github.zhenwei.wegoo.network.entity.BaseMessage;
import com.github.zhenwei.wegoo.network.entity.serialize.DefaultSerialize;
import com.github.zhenwei.wegoo.network.entity.serialize.SerializeBean;
import com.github.zhenwei.wegoo.network.entity.serialize.SerializeBean.SerializeMessage;
import com.github.zhenwei.wegoo.network.netty.handler.AbstractDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import lombok.val;

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

  private SerializeMessage serializeMessage;

  /**
   * @param [ctx, in, out]
   * @return void
   * @author zhangzhenwei
   * @description 序列化后将message传给下一个
   * @date 2022/1/3 21:34
   */
  @Override
  public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    val bodyLen = in.readInt();
    val body = new byte[bodyLen];
    in.readBytes(body);
    SerializeBean.build(body, BaseMessage.class,
        serializeMessage == null ? new DefaultSerialize() : serializeMessage);
    out.add(BaseMessage.serialize(body));
  }
}