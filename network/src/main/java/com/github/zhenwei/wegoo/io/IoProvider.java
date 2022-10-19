package com.github.zhenwei.wegoo.io;

/**
 * @author: zhangzhenwei
 * @description: IoProvider
 *  顶级IO处理类
 * @date: 2022/10/19  17:17
 * @since: 1.0
 */
public interface IoProvider {

    void write(byte[] data);

    byte[] read();

    void close();
}
