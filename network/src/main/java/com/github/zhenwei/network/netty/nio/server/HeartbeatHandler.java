package com.github.zhenwei.network.netty.nio.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

  /**
   * {@link io.netty.handler.timeout.IdleStateHandler} 触发后,执行此方法
   *
   * @param ctx
   * @param evt
   * @throws Exception
   */
  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof IdleStateEvent) {
      IdleStateEvent stateEvent = (IdleStateEvent) evt;
      System.out.println("当前通道: "+ctx.channel().toString());
      switch (stateEvent.state()) {
        case READER_IDLE:
          System.out.println("READER_IDLE");
          break;
        case WRITER_IDLE:
          System.out.println("WRITER_IDLE");
          break;
        case ALL_IDLE:
          System.out.println("ALL_IDLE");
          break;
        default:
          break;

      }

    }


  }

}