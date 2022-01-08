package com.github.zhenwei.wegoo.network.entity.serialize;

public class DefaultSerialize implements SerializeHandler {

  @Override
  public <T> T serialize(byte[] msg, Class<T> clazz) {
    //todo i will do something
    //json? proto?
    return null;
  }
}