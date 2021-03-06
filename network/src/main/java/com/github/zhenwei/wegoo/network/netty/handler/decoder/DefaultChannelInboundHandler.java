package com.github.zhenwei.wegoo.network.netty.handler.decoder;

import com.github.zhenwei.wegoo.network.netty.entity.BaseMessage;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description: 最后核心业务处理
 * @author: zhangzhenwei
 * @date: 2021/12/29 22:06
 */
@Sharable
public class DefaultChannelInboundHandler extends SimpleChannelInboundHandler<BaseMessage> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, BaseMessage msg) throws Exception {
    System.out.println(msg);
  }
}