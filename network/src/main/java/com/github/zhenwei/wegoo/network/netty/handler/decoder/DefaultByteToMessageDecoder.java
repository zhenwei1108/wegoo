package com.github.zhenwei.wegoo.network.netty.handler.decoder;

import com.github.zhenwei.wegoo.network.netty.handler.AbstractBaseMessageDecoder;
import com.github.zhenwei.wegoo.network.netty.serialize.NettySerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import lombok.val;

/**
 * @description: 入站使用, 用于消息解码,将byte[] 转为 执行对象
 * @author: zhangzhenwei
 * @date: 2021/12/26 21:56
 * @see io.netty.handler.codec.ReplayingDecoder 重写了callDecode 方法,调用其中decodeRemovalReentryProtection()
 * 方法
 */
@Sharable
public class DefaultByteToMessageDecoder extends AbstractBaseMessageDecoder<ByteBuf> {
  //todo 何时填充
  private NettySerializer serializer;

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
    out.add(serializer.deserialize(body));
  }
}