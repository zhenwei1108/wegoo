package com.github.zhenwei.wegoo.io;

public interface IoProvider {

    void write(byte[] data);

    byte[] read();

    void close();
}
