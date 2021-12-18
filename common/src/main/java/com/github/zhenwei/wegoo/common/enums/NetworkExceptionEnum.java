package com.github.zhenwei.wegoo.common.enums;

public enum NetworkExceptionEnum implements IException {
  NETWORK_ERR("network error", 899999),

  ;

  private final String message;
  private final int code;

  NetworkExceptionEnum(String message, int code) {
    this.message = message;
    this.code = code;
  }

  @Override
  public String getErrorMessage() {
    return this.message;
  }

  @Override
  public int getErrorCode() {
    return this.code;
  }
}