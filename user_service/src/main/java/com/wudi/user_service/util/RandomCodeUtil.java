package com.wudi.user_service.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @Description:
 * @Author: WUDI
 * @Date: Created In 2019-04-12
 */
public class RandomCodeUtil {

  /**
   * 数字
   */
  public static final int MODE_NUMBER = 1;

  /**
   * 字母
   */
  public static final int MODE_ALPHABET = 2;

  /**
   * 混合
   */
  public static final int MODE_MIXED = 3;

  private static final String[] codes = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8",
      "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
      "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
      "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

  private static final Random rand = new Random();

  private static String getRandomCode(int mode, int len) {
    if (len >= 0 && len <= 1000) {
      StringBuilder sb = new StringBuilder();
      int i;
      if (MODE_NUMBER == mode) {
        for (i = 0; i < len; ++i) {
          sb.append(codes[rand.nextInt(10)]);
        }
      } else if (MODE_ALPHABET == mode) {
        for (i = 0; i < len; ++i) {
          sb.append(codes[10 + rand.nextInt(52)]);
        }
      } else {
        for (i = 0; i < len; ++i) {
          sb.append(codes[rand.nextInt(codes.length)]);
        }
      }

      return sb.toString();
    } else {
      throw new IllegalArgumentException("长度错误");
    }
  }

  /**
   * 纯数字随机数
   */
  public static String getRandCodeWithNumber(int len) {
    return getRandomCode(MODE_NUMBER, len);
  }

  /**
   * 纯字母随机数
   *
   * @param upperCase 是否将生产的随机数转成大写,true=转换；false=不转换
   */
  public static String getRandCodeWithAlphabet(int len, boolean upperCase) {
    String randomCode = getRandomCode(MODE_ALPHABET, len);
    return !upperCase ? randomCode : randomCode.toUpperCase();
  }

  /**
   * 数字+字母随机数
   *
   * @param upperCase 是否将生产的随机数转成大写,true=转换；false=不转换
   */
  public static String getRandCodeWithMixed(int len, boolean upperCase) {
    String randomCode = getRandomCode(MODE_MIXED, len);
    return !upperCase ? randomCode : randomCode.toUpperCase();
  }

  /**
   * 返回一个字符和数字混合的字符串
   * @param numberRange 返回字符串中数字最多占比是多少个
   * @param length   返回字符串的最大长度
   * @param upperCase 是否转大写
   * @return
   */
  public static  String getMixedRandCode(int numberRange,int length,boolean upperCase){
    if (numberRange < length && numberRange >= 1 && length <= 1000){
      int numberCount  =  rand.nextInt(numberRange)+1;
      String numberStr =  getRandCodeWithNumber(numberCount);
      numberStr = numberStr + getRandCodeWithAlphabet(length - numberCount,upperCase);
      List<String> strList = new ArrayList<>();
      for (int i = 0 ;i< numberStr.length(); i++){
        strList.add(numberStr.charAt(i)+"");
      }
      //打乱顺序
      Collections.shuffle(strList);
      StringBuilder sb = new StringBuilder();
      for (String str : strList){
        sb.append(str);
      }
      return sb.toString();
    }else {
      throw new IllegalArgumentException("长度错误");
    }
  }

  public static void main(String[] args) {
    System.out.println(getRandCodeWithMixed(6, true));
    System.out.println(getRandCodeWithAlphabet(6, true));
    System.out.println(getRandCodeWithNumber(6));

    System.out.println(getMixedRandCode(5,6,true));
  }


}
