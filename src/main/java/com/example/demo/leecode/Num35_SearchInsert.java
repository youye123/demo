package com.example.demo.leecode;

public class Num35_SearchInsert {

  public static void main(String[] args) {
    int nums[] = {1,2,3,4,5,8,9};
    int target = -1;
    System.out.println(searchInsert(nums,target));
  }

  public static int searchInsert(int[] nums, int target) {

    int begin = 0;
    int end = nums.length - 1;
    while (begin <= end) {
      int mid = (begin + end) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] > target) {
        end = mid - 1;
      }else {
        begin = mid + 1;
      }
    }
    return begin;
  }

}
