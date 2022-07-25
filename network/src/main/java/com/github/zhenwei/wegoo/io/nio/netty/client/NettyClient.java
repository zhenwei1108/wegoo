package com.github.zhenwei.wegoo.io.nio.netty.client;

import com.github.zhenwei.wegoo.io.nio.NioClientProvider;

public class NettyClient implements NioClientProvider {

    @Override
    public void write(byte[] data) {

    }

    @Override
    public byte[] read() {
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
