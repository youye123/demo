package com.example.demo.leecode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Num20_IsValid {

  public static void main(String[] args) {
    String s = "(]";
    System.out.println(isValid(s));
  }

  public static boolean isValid(String s) {

    int n = s.length();
    if (n % 2 == 1) {
      return false;
    }
    Map<String, String> map = new HashMap<>();
    map.put("{", "}");
    map.put("[", "]");
    map.put("(", ")");

    Stack<String> v = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      if (map.containsKey(s.substring(i, i + 1))) {
        v.push(s.substring(i, i + 1));
        continue;
      }
      if (v.isEmpty()) {
        return false;
      }
      String x = v.pop();
      if (!map.get(x).equals(s.substring(i, i + 1))) {
        return false;
      }
    }

    return v.isEmpty();
  }
}
