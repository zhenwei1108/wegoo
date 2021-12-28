package com.github.zhenwei.wegoo.common.util;

public class StringUtils {


  /**
   * @param [msg]
   * @return boolean
   * @author zhangzhenwei
   * @description 判断是否为空
   * @date 2021/12/28 23:00
   */
  public static boolean isEmpty(String msg) {
    return msg == null || msg.length() == 0;
  }

  /**
   * @param [msg]
   * @return boolean
   * @author zhangzhenwei
   * @description 补充空串的校验
   * @date 2021/12/28 23:00
   */
  public static boolean isBlank(String msg) {
    return isEmpty(msg) || msg.trim().length() == 0;
  }
}