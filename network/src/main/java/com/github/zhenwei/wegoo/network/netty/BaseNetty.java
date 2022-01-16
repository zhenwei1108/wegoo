package com.github.zhenwei.wegoo.network.netty;

/**
 * @description: 没有想好  read怎么操作. 使用Future?
 * @author: zhangzhenwei
 * @date: 2022/1/14 09:39
 */
public interface BaseNetty<T> {

  void send(T t);

  void close();


}