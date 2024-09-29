package com.example.demo.leecode;

public class Num21_MergeTwoList {

  public static void main(String[] args) {

  }

  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    ListNode result = new ListNode();
    if(list1 == null) {
      result = list2;
      return result;
    }
    if(list2 == null) {
      result = list1;
      return result;
    }

    if(list1.val <= list2.val) {
      result.val = list1.val;
      if(list1.next != null){
        list1 = list1.next;
      }else{
        result.next = list2;
        return result;
      }
    }else{
      result.val = list2.val;

      if(list2.next != null){
        list2 = list2.next;
      }else {
        result.next = list1;
        return result;
      }
    }

    result.next = mergeTwoLists(list1,list2);
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
