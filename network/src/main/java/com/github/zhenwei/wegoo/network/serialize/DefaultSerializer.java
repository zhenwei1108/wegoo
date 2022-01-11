package com.github.zhenwei.wegoo.network.serialize;


import com.google.protobuf.MessageLite;
import lombok.AllArgsConstructor;

/**
 * @description: 默认序列化实现
 *  需要引入 Protobuf-java 依赖
 * @author: zhangzhenwei
 * @date: 2022/1/8 22:02
 */
@AllArgsConstructor
public class DefaultSerializer<T extends MessageLite> implements NettySerializer<T> {

  private final MessageLite prototype;

  @Override
  public byte[] serialize(T o) throws Exception {

    return new byte[0];
  }

  @Override
  public T deserialize(byte[] bytes) throws Exception {
    return (T) prototype.getParserForType().parseFrom(bytes);
  }
}