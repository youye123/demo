package com.example.demo.leecode;

public class Num14_LongestCommonPrefix {

  public static void main(String[] args) {
    String[] strs = new String[]{"dog","dogracecar","dogcar"};
    System.out.println(longestCommonPrefix(strs));
  }

  public static String longestCommonPrefix(String[] strs) {
    String prefix = "";

    for (int i = 0; i < 200; i++) {
      if (i == strs[0].length() + 1) {
        return prefix;
      }
      String temp = strs[0].substring(0, i);
      for (int j = 1; j < strs.length; j++) {
        if (i == strs[j].length() + 1 || !strs[j].startsWith(temp)) {
          return prefix;
        }
      }
      prefix = temp;
    }

    return prefix;
  }

}
