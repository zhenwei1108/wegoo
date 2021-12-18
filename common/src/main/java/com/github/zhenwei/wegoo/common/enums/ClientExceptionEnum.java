package com.github.zhenwei.wegoo.common.enums;

public enum ClientExceptionEnum implements IException {

  CLIENT_ERR("client error",199999)

  ;

  private final String message;
  private final int code;

  ClientExceptionEnum(String message, int code) {
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