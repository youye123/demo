package com.example.demo.leecode;

public class Num704_BinarySearch {

  public static void main(String[] args) {
    int[] nums = {-1, 0, 3, 5, 9, 12};
    int target = 9;
    System.out.println(binarySearch(nums,target));

  }

  public static int binarySearch(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      if (nums[left] == target) {
        return left;
      }
      int mid = (right + left) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      if (nums[mid] > target) {
        right = mid - 1;
      }
      if (nums[mid] < target) {
        left = mid + 1;
      }
    }
    return -1;
  }
}
