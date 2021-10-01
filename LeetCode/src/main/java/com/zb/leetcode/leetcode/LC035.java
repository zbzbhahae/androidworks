package com.zb.leetcode.leetcode;

import com.zb.leetcode.utils.P;

public class LC035 {
    public static void main(String[] args) {
        P.p("123");
//        searchInsert(new int[]{1,3,4,7}, 2);

        boolean flag = true;
    }

    public static int searchInsert(int[] nums, int target) {
        if(null == nums || 0 == nums.length) {
            return 0;
        }
        int left = 0;
        int right = nums.length-1;
        while(left < right && (right - left) > 1) {
            int middle = (int)((left + right) / 2 + 0.5f);
            if(target > nums[middle]) {
                left = middle;
            } else if(target < nums[middle]) {
                right = middle;
            } else {
                return middle;
            }
        }

        return right;
    }
}
