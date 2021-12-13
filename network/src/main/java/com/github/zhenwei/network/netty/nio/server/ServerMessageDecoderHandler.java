package com.github.zhenwei.network.netty.nio.server;

import com.github.zhenwei.network.netty.nio.proto.PersionEntity;
import com.github.zhenwei.network.netty.nio.proto.PersionEntity.Persion;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 *
 * @see io.netty.buffer.Unpooled
 * 使用 Unpooled操作ByteBuf.
 */
public class ServerMessageDecoderHandler extends ByteToMessageDecoder {

    /**
     * 管理所有链接,并可以统一进行处理
     */
    private ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //接受消息处理
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //可读长度, 默认 2048? 字节后被分包
        int readableLength = in.readableBytes();
        byte[] data = new byte[readableLength];
        in.readBytes(data);
        String message = new String(data, StandardCharsets.UTF_8);
        out.add(message);
        ctx.writeAndFlush("i have received your message: " + message);
        System.out.println("服务端收到并应答消息：" + message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PersionEntity.Persion persion = (Persion) msg;
        System.out.println("收到客户端传入对象:"+persion);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务端处理错误");
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("消息读取完毕后,执行此方法");

    }


    /**
     * 链接被建立时候,触发此方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //遍历所有的 channel,并发送消息.
        channels.writeAndFlush("ctx:"+ctx+" connected");
        channels.add(ctx.channel());
    }


}