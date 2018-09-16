package com.huarui.lic;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesUtils
{
  protected static final String SECRETKEY = "21huarui.com";

  public static void main(String[] args)
    throws Exception
  {
    String strTestData = "明文123456";

    String encryptResultStr = encryptStr(strTestData, "21huarui.com");
    System.out.println("加密后：" + encryptResultStr);

    String decryptResultStr = decryptStr(encryptResultStr);
    System.out.println("解密后：" + decryptResultStr);
  }

  protected static String encryptStr(String srcStr, String password)
  {
    byte[] encryptResult = encryptData_AES(srcStr, password);
    String encryptResultStr = parseByte2HexStr(encryptResult);
    return encryptResultStr;
  }

  public static String decryptStr(String srcStr)
  {
    String returnValue = "";
    try
    {
      byte[] decryptFrom = parseHexStr2Byte(srcStr);
      byte[] decryptResult = decryptData_AES(decryptFrom, "21huarui.com");
      returnValue = new String(decryptResult, "utf-8");
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    return returnValue;
  }

  private static String parseByte2HexStr(byte[] buf)
  {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < buf.length; ++i)
    {
      String hex = Integer.toHexString(buf[i] & 0xFF);
      if (hex.length() == 1)
        hex = '0' + hex;

      sb.append(hex.toUpperCase());
    }
    return sb.toString();
  }

  private static byte[] encryptData_AES(String content, String password)
  {
    SecretKey secretKey;
    try {
      secretKey = getKey(password);
      byte[] enCodeFormat = secretKey.getEncoded();
      SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
      Cipher cipher = Cipher.getInstance("AES");
      byte[] byteContent = content.getBytes("utf-8");
      cipher.init(1, key);
      return cipher.doFinal(byteContent);
    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    catch (NoSuchPaddingException e)
    {
      e.printStackTrace();
    }
    catch (InvalidKeyException e)
    {
      e.printStackTrace();
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    catch (IllegalBlockSizeException e)
    {
      e.printStackTrace();
    }
    catch (BadPaddingException e)
    {
      e.printStackTrace();
    }
    catch (GeneralSecurityException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  private static byte[] parseHexStr2Byte(String hexStr)
  {
    if (hexStr.length() < 1)
      return null;

    byte[] result = new byte[hexStr.length() / 2];
    for (int i = 0; i < hexStr.length() / 2; ++i)
    {
      int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
      int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 
        16);
      result[i] = (byte)(high * 16 + low);
    }
    return result;
  }

  private static SecretKey getKey(String secret) throws GeneralSecurityException
  {
    KeyGenerator _generator;
    try
    {
      _generator = KeyGenerator.getInstance("AES");
      SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
      secureRandom.setSeed(secret.getBytes());
      _generator.init(128, secureRandom);
      return _generator.generateKey();
    }
    catch (Exception e)
    {
      throw new RuntimeException("初始化密钥出现异�?");
    }
  }

  private static byte[] decryptData_AES(byte[] content, String password)
  {
    SecretKey secretKey;
    try {
      secretKey = getKey(password);
      byte[] enCodeFormat = secretKey.getEncoded();
      SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(2, key);
      return cipher.doFinal(content);
    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    catch (NoSuchPaddingException e)
    {
      e.printStackTrace();
    }
    catch (InvalidKeyException e)
    {
      e.printStackTrace();
    }
    catch (IllegalBlockSizeException e)
    {
      e.printStackTrace();
    }
    catch (BadPaddingException e)
    {
      e.printStackTrace();
    }
    catch (GeneralSecurityException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}