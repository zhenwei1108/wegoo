package com.github.zhenwei.wegoo.network.entity;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
/**
 * @description: 使用proto实现
 * @author: zhangzhenwei
 * @date: 2022/1/13 09:28
 */
public class ObjectMessage extends BaseMessage implements Serializable {

  @Override
  public void writeTo(CodedOutputStream output) throws IOException {
    
  }

  @Override
  public int getSerializedSize() {
    return 0;
  }

  @Override
  public Parser<? extends MessageLite> getParserForType() {
    return null;
  }

  @Override
  public ByteString toByteString() {
    return null;
  }

  @Override
  public byte[] toByteArray() {
    return new byte[0];
  }

  @Override
  public void writeTo(OutputStream output) throws IOException {

  }

  @Override
  public void writeDelimitedTo(OutputStream output) throws IOException {

  }

  @Override
  public Builder newBuilderForType() {
    return null;
  }

  @Override
  public Builder toBuilder() {
    return null;
  }

  @Override
  public MessageLite getDefaultInstanceForType() {
    return null;
  }

  @Override
  public boolean isInitialized() {
    return false;
  }
}