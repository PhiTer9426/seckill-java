package com.zhao.seckill.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
  SUCCESS(200, "SUCCESS"),

  ERROR(500, "Internal error"),
  LOGIN_ERROR(500210, "用户名密码不正确"),
  LOGIN_MOBILE_ERROR(500211, "手机号码格式不正确"),
  LOGIN_NO_USER_ERROR(500212, "用户不存在"),
  BIND_ERROR(500212, "参数效验异常"),

  EMPTY_STOCK(500500, "商品无货"),
  REPEAT_ERROR(500501, "该商品只能抢购一件")
  ;

  private final Integer code;
  private final String message;

}
