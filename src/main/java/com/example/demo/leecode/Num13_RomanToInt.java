package com.example.demo.leecode;

import java.util.HashMap;
import java.util.Map;

public class Num13_RomanToInt {

  public static void main(String[] args) {
    String roman = "VIII";
    System.out.println(romanToInt(roman));
  }

  public static int romanToInt(String s) {
    Map<String, Integer> map = new HashMap<>();
    map.put("I", 1);
    map.put("V", 5);
    map.put("X", 10);
    map.put("L", 50);
    map.put("C", 100);
    map.put("D", 500);
    map.put("M", 1000);

    int result = 0;
    for (int i = 0; i < s.length(); i++) {
      String key = s.substring(i, i + 1);
      if (map.containsKey(key)) {
        if (i == s.length() - 1) {
          result += map.get(key);
          return result;
        }
        if (map.get(key) < map.get(s.substring(i + 1, i + 2))) {
          result = result - map.get(key) + map.get(s.substring(i + 1, i + 2));
          i++;
        } else {
          result += map.get(key);
        }
      }
    }

    return result;
  }
}
