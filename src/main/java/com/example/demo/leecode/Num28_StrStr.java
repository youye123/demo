package com.example.demo.leecode;

public class Num28_StrStr {

  public static void main(String[] args) {
    String haystack = "mississippi";
    String needle = "issip";
    System.out.println(strStr(haystack, needle));
  }

  public static int strStr(String haystack, String needle) {

    for (int i = 0; i < haystack.length() - needle.length() + 1;i++) {
      for (int j = 0; j < needle.length(); j++) {
        if(haystack.charAt(i + j) != needle.charAt(j)) {
          break;
        }else if(j == needle.length()-1){
          return i;
        }
      }
    }
    return -1;
  }

}
