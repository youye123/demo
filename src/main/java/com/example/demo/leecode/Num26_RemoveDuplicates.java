package com.example.demo.leecode;

public class Num26_RemoveDuplicates {

  public static void main(String[] args) {
    int[] nums = {0,0,1,1,1,2,2,3,3,4};
    int k = removeDuplicates(nums);
  }

  public static int removeDuplicates(int[] nums) {

    int newNums[] = new int[nums.length];
    int head = 0;
    for (int i = 0; i < nums.length; i++) {
      if(i == nums.length - 1){
        newNums[head] = nums[i];
        break;
      }

      if (nums[i] != nums[i + 1] ) {
        newNums[head] = nums[i];
        head++;
      }
    }
    nums = newNums;
    return head + 1;
  }

}
