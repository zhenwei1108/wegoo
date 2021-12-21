package com.github.zhenwei.wegoo.common.exception;

import com.github.zhenwei.wegoo.common.enums.NetworkExceptionEnum;


public class NetworkException extends BaseException{

  public NetworkException(NetworkExceptionEnum iException) {
    super(iException);
  }

  public NetworkException(NetworkExceptionEnum iException, Throwable cause) {
    super(iException, cause);
  }
}