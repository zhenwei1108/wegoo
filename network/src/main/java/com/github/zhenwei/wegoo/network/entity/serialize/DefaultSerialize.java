package com.github.zhenwei.wegoo.network.entity.serialize;

import com.github.zhenwei.wegoo.network.entity.serialize.SerializeBean.SerializeMessage;

public class DefaultSerialize implements SerializeMessage {

  @Override
  public <T> T serialize(byte[] msg, Class<T> clazz) {
    //todo i will do something
    return null;
  }
}