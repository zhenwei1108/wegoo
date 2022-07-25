package com.github.zhenwei.wegoo.io.nio.netty.server;

import com.github.zhenwei.wegoo.io.nio.NioServerProvider;

public class NettyServer implements NioServerProvider {

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
