package com.zhao.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class MD5Util {
  public static String md5(String src) {
    return DigestUtils.md5Hex(src);
  }

  private static final String salt = "1a2b3c4d";

  public static String inputPwdToFromPwd(String input) {
    String str = "" + salt.charAt(0) + salt.charAt(3) + input + salt.charAt(2) + salt.charAt(5);
    return md5(str);
  }

  public static String fromPwdToDBPwd(String from, String salt) {
    String str = "" + salt.charAt(0) + salt.charAt(3) + from + salt.charAt(2) + salt.charAt(5);
    return md5(str);
  }

  public static String inputPwdToDBPwd(String input, String salt) {
    String from = inputPwdToFromPwd(input);
    String DBPwd = fromPwdToDBPwd(from, salt);
    return DBPwd;
  }

  public static void main(String[] args) {
    System.out.println(inputPwdToFromPwd("123456"));
    System.out.println(fromPwdToDBPwd(inputPwdToFromPwd("123456"), "1a2b3c4d"));
    System.out.println(inputPwdToDBPwd("123456", "1a2b3c4d"));
  }
}
