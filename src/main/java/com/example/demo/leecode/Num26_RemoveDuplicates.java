package com.example.demo.leecode;

import java.util.ArrayList;
import java.util.Arrays;

public class Num26_RemoveDuplicates {

  public static void main(String[] args) {
    int[] nums = {0,0,1,1,1,2,2,3,3,4};
    System.out.println(removeDuplicates(nums));
  }

  public static int removeDuplicates(int[] nums) {
    if(nums.length < 2) {
      return nums.length;
    }
    int k = 1;
    for (int i = 1; i < nums.length; i++) {

      if (nums[i] != nums[i - 1]) {
        nums[k++] = nums[i];
      }
    }
    return k;
  }
}
