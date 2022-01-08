package com.github.zhenwei.wegoo.network.entity.serialize;

/**
 * @description: 序列化
 * @author: zhangzhenwei
 * @date: 2022/1/3 21:31
 */
public class SerializeBean {
  /**
   * @param [msg, clazz, serializeMessage]
   * @return T
   * @author zhangzhenwei
   * @description 通过序列化构造应答类
   * @date 2022/1/3 21:31
   */
  public static<T> T build(byte[] msg, Class<T> clazz, SerializeHandler serializeHandler){
    return serializeHandler.serialize(msg, clazz);
  }

}