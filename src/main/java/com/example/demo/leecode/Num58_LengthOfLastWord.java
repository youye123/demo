package com.example.demo.leecode;

public class Num58_LengthOfLastWord {

  public static void main(String[] args) {
    String s = "Hello World ";
    System.out.println(lengthOfLastWord(s));
  }

  public static int lengthOfLastWord(String s) {

    String blankStr = " ";
    // 去掉末尾空格
    while (s.endsWith(blankStr)) {
      s = s.substring(0, s.length() - 1);
    }
    if (s.length() == 0) {
      return 0;
    }
    int n = 0;
    while (!s.endsWith(blankStr)) {
      s = s.substring(0, s.length() - 1);
      n++;
    }


    return n;
  }
}
