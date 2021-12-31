package com.github.zhenwei.network.netty.nio.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @see io.netty.buffer.Unpooled 使用 Unpooled操作ByteBuf.
 */
public class ServerMessageDecoderHandler extends ReplayingDecoder<Void> {


  private final NioEventLoopGroup group = new NioEventLoopGroup(16);

  /**
   * 管理所有链接,并可以统一进行处理
   */
  private ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

  //接受消息处理

  /**
   * 若接收消息过大, 则此方法会被调用多次. 默认1024?2048字节? 使用{@link io.netty.handler.codec.ReplayingDecoder} 解决分包问题
   * {@linkplain io.netty.channel.AbstractChannelHandlerContext#fireChannelRead(Object)}} 触发
   */
  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    //todo 根据 in.readerIndex();  in.writerIndex() 进行判断
    //todo ReplayingDecoder 的bytebuf被调整
    //可读长度, 默认 2048? 字节后被分包
    byte[] data = new byte[11264];
    in.readBytes(data);
    String message = new String(data, StandardCharsets.UTF_8);
    out.add(message);
    ctx.writeAndFlush("i have received your message: " + message);
    System.out.println("1服务端收到并应答消息：" + message);
  }

  /**
   * 由 {@linkplain io.netty.channel.AbstractChannelHandlerContext#fireChannelRead(Object)} 触发
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("老子覆盖了父类的实现,不会调用本实现的decode方法");
    super.channelRead(ctx, msg);
//    group.execute(() -> {
//      //异步处理
//      PersionEntity.Persion persion = (Persion) msg;
//      System.out.println("收到客户端传入对象:" + persion);
//    });

  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    System.out.println("服务端处理错误");
    cause.printStackTrace();
    ctx.close();
  }

  /**
   * 由 {@linkplain io.netty.channel.AbstractChannelHandlerContext#fireChannelReadComplete()} 触发
   */
  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    System.out.println("消息读取完毕后,执行此方法");

  }


  /**
   * 链接被建立时候,触发此方法 {@linkplain io.netty.channel.DefaultChannelPipeline#addLast(ChannelHandler)} 触发
   */
  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    //遍历所有的 channel,并发送消息.
    channels.writeAndFlush("ctx:" + ctx + " connected");
    channels.add(ctx.channel());
  }


}