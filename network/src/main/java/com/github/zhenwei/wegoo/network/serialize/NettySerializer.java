package com.github.zhenwei.wegoo.network.serialize;


public interface NettySerializer<T> {

  byte[] serialize(T t)  throws Exception;

  T deserialize(byte[] bytes) throws Exception;
}