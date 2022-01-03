package com.github.zhenwei.wegoo.network.entity;

import java.io.Serializable;

public class ObjectMessage extends BaseMessage implements Serializable {

  @Override
  public byte[] getEncode() {
    return new byte[0];
  }

}