package com.github.zhenwei.wegoo.network.serialize;


/**
 * @description: 默认序列化实现
 * @author: zhangzhenwei
 * @date: 2022/1/8 22:02
 */
public class DefaultSerializer implements NettySerializer {


  @Override
  public byte[] serialize(Object o) throws Exception {
    return new byte[0];
  }

  @Override
  public Object deserialize(byte[] bytes) throws Exception {
    return null;
  }
}