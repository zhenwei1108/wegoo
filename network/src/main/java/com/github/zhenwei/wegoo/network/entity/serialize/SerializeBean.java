package com.github.zhenwei.wegoo.network.entity.serialize;

import java.io.Serializable;

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
  public static<T> T build(byte[] msg, Class<T> clazz, SerializeMessage serializeMessage){
    return serializeMessage.serialize(msg, clazz);
  }


  /**
   * @description: 序列化接口
   * @author: zhangzhenwei
   * @date: 2022/1/3 21:30
   */
  public interface SerializeMessage {
    /**
     * @param [msg, clazz]
     * @return T
     * @author zhangzhenwei
     * @description 序列化
     * @date 2022/1/3 21:30
     */
    <T> T  serialize(byte[] msg, Class<T> clazz);
  }

}