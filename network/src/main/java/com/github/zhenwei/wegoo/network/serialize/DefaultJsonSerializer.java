package com.github.zhenwei.wegoo.network.serialize;

import com.google.gson.JsonObject;

public class DefaultJsonSerializer implements NettySerializer<JsonObject>{

  @Override
  public byte[] serialize(JsonObject jsonObject) throws Exception {
    //nothing todo
    return new byte[0];
  }

  @Override
  public JsonObject deserialize(byte[] bytes) throws Exception {
    return null;
  }
}