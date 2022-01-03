package com.github.zhenwei.wegoo.network.entity;

/**
 * @see io.netty.buffer.PooledUnsafeDirectByteBuf
 */
public abstract class BaseMessage {

  //todo 参考redis 填入序列化方法.
 public abstract byte[] getEncode();

 public static BaseMessage seriaze(byte[] msg){
   //todo serialize
   return null;
 }


}