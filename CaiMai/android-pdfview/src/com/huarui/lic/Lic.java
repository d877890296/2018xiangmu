package com.huarui.lic;

public class Lic
{
  public static String S1 = AesUtils.encryptStr("2017-1-1", "21huarui.com");
  public static String S2 = AesUtils.encryptStr("2018-4-18 15:33", "21huarui.com");
  public static String M1 = "系统使用期限已到，请与产品供应商联系！";
}