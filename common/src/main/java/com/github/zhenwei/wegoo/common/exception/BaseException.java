package com.github.zhenwei.wegoo.common.exception;

import com.github.zhenwei.wegoo.common.enums.IException;
import com.github.zhenwei.wegoo.common.util.StringUtils;

/**
 * 异常基类
 */
abstract class BaseException extends Exception {

  String message;
  int code;


  public BaseException(IException iException) {
    super(iException.getErrorMessage());
    this.code = iException.getErrorCode();
    this.message = iException.getErrorMessage();
  }

  public BaseException() {
    this.message = IException.SYSTEM_EXCEPTION_MESSAGE;
    this.code = IException.SYSTEM_EXCEPTION_CODE;
  }

  public BaseException(String message, int code) {
    super(message);
    this.code = code;
    this.message = message;
  }

  public BaseException(String message) {
    super(message);
    this.message = message;
    this.code = IException.SYSTEM_EXCEPTION_CODE;
  }

  public BaseException(String message, Throwable cause) {
    super(message, cause);
    this.message =
        StringUtils.isEmpty(cause.getMessage()) ? StringUtils.isEmpty(cause.getLocalizedMessage())
            ? message : cause.getLocalizedMessage() : cause.getMessage();
    this.code = IException.SYSTEM_EXCEPTION_CODE;
  }

  public BaseException(Throwable cause) {
    super(cause);
    this.message = StringUtils.isEmpty(cause.getMessage())
        ? cause.getLocalizedMessage() : cause.getMessage();
    this.code = IException.SYSTEM_EXCEPTION_CODE;
  }

  public BaseException(IException iException, Throwable cause) {
    super(cause);
    this.code = iException.getErrorCode();
    this.message =
        StringUtils.isEmpty(cause.getMessage()) ? StringUtils.isEmpty(cause.getLocalizedMessage())
            ? iException.getErrorMessage() : cause.getLocalizedMessage() : cause.getMessage();
  }
}