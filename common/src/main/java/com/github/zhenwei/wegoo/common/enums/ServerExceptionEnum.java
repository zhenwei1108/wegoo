package com.github.zhenwei.wegoo.common.enums;

public enum ServerExceptionEnum implements IException {
    SERVER_ERR("server error",299999)

  ;

  private final String message;
  private final int code;

  ServerExceptionEnum(String message, int code) {
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