package com.github.zhenwei.wegoo.common.exception;

import com.github.zhenwei.wegoo.common.enums.ServerExceptionEnum;

public class ServerException extends BaseException {

  public ServerException(ServerExceptionEnum iException) {
    super(iException);
  }

  public ServerException(ServerExceptionEnum iException, Throwable cause) {
    super(iException, cause);
  }
}