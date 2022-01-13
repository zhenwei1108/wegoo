package com.github.zhenwei.wegoo.network.netty;

public interface BaseNetty<T> {

  void send(T t);

  T read();



}