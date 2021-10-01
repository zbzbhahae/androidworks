package com.zb.leetcode.leetcode;

import com.zb.leetcode.utils.P;

import java.util.Arrays;

public class RemoveElement {

    public static void main(String args[]) {
        int[] nums = {1,2,3,4,5,6,4,3,4,4,4,4,4,4,4,4,6,4,5,234,4,5,6,6,43,4};
        P.p("length->" + removeElements(nums, 4) + "  " + Arrays.toString(nums));
    }

    public static int removeElements(int[] nums, int val) {
        if(null == nums || nums.length < 1)
            return 0;
        if(nums.length == 1 && nums[0] == val)
            return 0;

        int lengthCount = nums.length;
        int i=0;
        int j = nums.length-1;
        while (i < j) {
            if(nums[i] == val) {
                int tem = nums[i];
                nums[i] = nums[j];
                nums[j] = tem;
                j--;
            } else {
                i++;
            }
        }
        return j;
    }
}
