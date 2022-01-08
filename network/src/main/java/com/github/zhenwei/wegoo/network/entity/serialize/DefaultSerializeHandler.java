package com.github.zhenwei.wegoo.network.entity.serialize;

/**
 * @description: 默认序列化实现
 * @author: zhangzhenwei
 * @date: 2022/1/8 22:02
 */
public class DefaultSerializeHandler implements SerializeHandler {

  @Override
  public <T> T serialize(byte[] msg, Class<T> clazz) {
    //todo i will do something
    //json? proto?
    return null;
  }
}