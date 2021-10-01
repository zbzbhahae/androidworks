package com.zb.leetcode.leetcode;

import com.zb.leetcode.utils.P;

public class CombineNodes {

    public static void main(String args[]) {
        new CombineNodes().goCheck();

    }

    public void goCheck() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        mergeTwoLists(l1, l2);
        P.line();
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) {
          this.val = val;
      }
      ListNode(int val, ListNode next) {
          this.val = val; this.next = next;
      }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(null == l1)
            return l2;
        if(null == l2)
            return l1;

        ListNode root;
        ListNode tem = new ListNode();
        if(l1.val < l2.val) {
            root = l1;
        } else {
            root = l2;
        }


        while (l1 != null || l2 != null) {
            if(null != l1 && null != l2) {
                if(l1.val < l2.val) {
                    tem.next = l1;
                    tem = tem.next;
                    l1 = l1.next;
                } else {
                    tem.next = l2;
                    tem = tem.next;
                    l2 = l2.next;
                }
            } else if(null == l1) {
                tem.next = l2;
                l2 = null;
            } else {
                tem.next = l1;
                l1 = null;
            }

        }
        return root;

    }

}
