package com.example.demo.leecode;

public class Num9_Palindrome {

  public static void main(String[] args) {
    int[] x = new int[5];
    x[0] = 1;
    x[1] = 1211;
    x[2] = 11;
    x[3] = 0;
    x[4] = -11;
    for (int i = 0; i < x.length; i++) {
      System.out.println(isPalindrome(x[i]));
    }
  }

  public static boolean isPalindrome(int x) {
    if (x < 0) {
      return false;
    }
    String str = String.valueOf(x);
    if (str.length() == 1) {
      return true;
    }
    String left = str.substring(0, str.length() / 2);
    String right = str.length() % 2 == 0 ? str.substring(str.length() / 2) : str.substring(str.length() / 2 + 1);
    for (int i = 0; i < left.length(); i++) {
      if(left.charAt(i) != right.charAt(left.length() - i - 1)){
        return false;
      }
    }

    return true;
  }
}
