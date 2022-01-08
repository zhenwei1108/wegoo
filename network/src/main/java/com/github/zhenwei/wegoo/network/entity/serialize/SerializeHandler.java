package com.github.zhenwei.wegoo.network.entity.serialize;

/**
 * @description: 序列化接口
 * @author: zhangzhenwei
 * @date: 2022/1/8 21:58
 */
public interface SerializeHandler {
  /**
   * @param [msg, clazz]
   * @return T
   * @author zhangzhenwei
   * @description 序列化
   * @date 2022/1/3 21:30
   */
  <T> T  serialize(byte[] msg, Class<T> clazz);
}