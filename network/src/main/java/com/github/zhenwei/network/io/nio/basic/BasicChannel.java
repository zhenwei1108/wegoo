package com.github.zhenwei.network.io.nio.basic;


import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.channels.ServerSocketChannel;

/**
 * {@link java.nio.channels.Channel}
 */
public class BasicChannel {

    public void byteChannel() throws IOException {
        //ServerSocketChannelImpl
        ServerSocketChannel serverSocketChannel =ServerSocketChannel.open();
        //发送缓冲区大小
        serverSocketChannel.setOption(StandardSocketOptions.SO_SNDBUF,1);

    }


}
