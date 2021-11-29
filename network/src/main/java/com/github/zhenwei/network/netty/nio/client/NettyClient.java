package com.github.zhenwei.network.netty.nio.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyClient {

    private ChannelFuture future;

    public NettyClient build(String ip, int port) throws Exception {
        Bootstrap client = new Bootstrap().group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .handler(
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel channel) throws Exception {
                                channel.pipeline()
                                        .addLast(new LoggingHandler(LogLevel.TRACE),
                                                //输出 处理
                                                new ClientMessageEncoderHandler(),
                                                //输入 处理
                                                new ClientMessageDecoderHandler()
                                        );


                            }
                        });
        future = client.connect(ip, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("客户端启动");
            } else {
                System.out.println("客户端链接失败,断开链接");
                close();
            }
        }).sync();
        return this;
    }

    public void close() {
        if (future != null) {
            try {
                future.channel().closeFuture().sync();
            } catch (Exception ignore) {
            } finally {
                future = null;
            }
        }
    }

    public void sendMessage(String message) throws InterruptedException {
        future.channel().writeAndFlush(message);
        System.out.println("客户端发送数据：" + message);
        future.channel().closeFuture().sync();
    }

    //Future
    public String readMessage() {
        Channel read = future.channel().read();
        return null;

    }


}