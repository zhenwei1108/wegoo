package com.github.zhenwei.wegoo.common.exception;

import com.github.zhenwei.wegoo.common.enums.ClientExceptionEnum;

public class ClientException extends BaseException{

  public ClientException(ClientExceptionEnum iException) {
    super(iException);
  }

  public ClientException(ClientExceptionEnum iException, Throwable cause) {
    super(iException, cause);
  }
}