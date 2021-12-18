package com.github.zhenwei.wegoo.common.enums;

/**
 * 异常接口
 */
public interface IException {

  String SYSTEM_EXCEPTION_MESSAGE = "system exception";
  int SYSTEM_EXCEPTION_CODE = 999999;


  String getErrorMessage();

  int getErrorCode();

}