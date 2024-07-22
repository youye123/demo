package com.example.demo.leecode;

public class Num21_MergeTwoList {

  public static void main(String[] args) {

  }

  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    ListNode result = new ListNode();
    if(list1 == null) {
      result = list2;
    }
    if(list2 == null) {
      result = list1;
    }



    return result;
  }

  public static class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}
