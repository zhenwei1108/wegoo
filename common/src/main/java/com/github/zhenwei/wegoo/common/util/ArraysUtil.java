package com.github.zhenwei.wegoo.common.util;

public class ArraysUtil {

  public static boolean isEmpty(Object[] objects) {
    return objects == null || objects.length == 0;
  }

  public static boolean notEmpty(Object[] objects) {
    return !isEmpty(objects);
  }

}