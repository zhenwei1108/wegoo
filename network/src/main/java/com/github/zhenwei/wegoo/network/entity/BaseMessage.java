package com.github.zhenwei.wegoo.network.entity;

import java.io.Serializable;

/**
 * @see io.netty.buffer.PooledUnsafeDirectByteBuf
 */
public abstract class BaseMessage implements Serializable {

  //todo 参考redis 填入序列化方法.
 public abstract byte[] getEncode();

 public static BaseMessage serialize(byte[] msg){
   //todo serialize
   return null;
 }


}