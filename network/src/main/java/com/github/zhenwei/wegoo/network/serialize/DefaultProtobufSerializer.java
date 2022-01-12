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
public class DefaultProtobufSerializer implements NettySerializer<MessageLite> {

  private final MessageLite prototype;

  @Override
  public byte[] serialize(MessageLite e) throws Exception {
    return e.toByteArray();
  }

  @Override
  public MessageLite deserialize(byte[] bytes) throws Exception {
    return prototype.getParserForType().parseFrom(bytes);
  }
}