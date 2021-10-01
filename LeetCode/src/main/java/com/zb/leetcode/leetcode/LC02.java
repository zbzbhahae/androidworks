package com.zb.leetcode.leetcode;

import com.zb.leetcode.utils.P;
import com.zb.leetcode.utils.TimeCheck;

import java.util.ArrayList;
import java.util.Stack;

public class LC02 {

    public static void main(String[] args) {

        ListNode l1 = createLinkedList("57392658236532414324");
        ListNode l2 = createLinkedList("9574975896438648216486382");


        TimeCheck.check("addTwoNumbers", new TimeCheck.CheckCallback() {
            @Override
            public void callFun() {
                P.p(addTwoNumbers(l1, l2).toString());
            }
        });

    }

    static ListNode createLinkedList(String numbers) {
        char[] nums = numbers.toCharArray();
        ListNode start = new ListNode(Integer.valueOf(nums[0] + ""), null);
        ListNode prev = start;
        for(int i=1; i< nums.length; i++) {
            ListNode current = new ListNode(Integer.valueOf(nums[i] + ""), null);
            prev.next = current;
            prev = current;
        }
        return start;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(null == l1)
            return l2;
        if(null == l2)
            return l1;
        int inFlag = 0;

        ListNode l = l1;
        ListNode start = l;
        ListNode zeroNode = new ListNode(0, null);
        while (l1 != null || l2 != null) {
            l.val = l1.val + l2.val + inFlag;
            if(l.val > 9) {
                inFlag = 1;
            } else {
                inFlag = 0;
            }
            l.val %= 10;
            if(l1.next == null) {
                l1 = zeroNode;
                if(l2.next == null) {
                    break;
                }
                l2 = l2.next;
                l.next = l2;
                l = l.next;
            } else if(l2.next == null) {

                l2 = zeroNode;
                if(l1.next == null)
                    break;
                l1 = l1.next;
                l.next = l1;
                l = l.next;
            } else {
                l1 = l1.next;
                l2 = l2.next;
                l.next = l1;
                l = l.next;
            }
        }
        if(inFlag != 0) {
            ListNode last = new ListNode(inFlag, null);
            l.next = last;
        }
        return start;
    }


    static class ListNode {
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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode current = this;
            sb.append("[ ");
            while(current != null) {
                sb.append(", " + current.val);
                current = current.next;
            }
            sb.append("] ");
            return sb.toString();
        }
    }
}
