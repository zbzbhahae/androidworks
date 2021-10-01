package com.zb.leetcode.leetcode;

import com.zb.leetcode.utils.P;

import java.util.Arrays;

public class DeleteElementsInArray {

    public static void main(String args[]) {
        int[] nums = {1,1,1};
        P.p("length-> " + removeDuplicates(nums) + "  nums:" + Arrays.toString(nums));
    }

    public static int removeDuplicates(int[] nums) {
        int length = nums.length;
        int lengthCount = length;
        int commonCount = 0;
        int SIGN = Integer.MAX_VALUE;
        boolean isLastMax = false;
        if(length < 1)
            return 0;
        if(nums[length-1] == SIGN)
            isLastMax = true;
        for(int i=0; i<length - 1; i++) {
            int first = nums[i];
            for(int j=i+1; j<length; j++) {
                int second = nums[j];
                if(first == second) {
                    nums[j] = SIGN;
                    lengthCount--;
                    commonCount++;
                }
                i += commonCount;
                commonCount = 0;
            }
        }

        for(int i=0; i<length - 1; i++) {
            if(nums[i] == SIGN) {
                for (int j = i + 1; j < length; j++) {
                    if(nums[j] != SIGN) {
                        nums[i] = nums[j];
                        nums[j] = SIGN;
                        break;
                    }
                }
            }
        }

        if(isLastMax) {
            if(nums[lengthCount - 1] != Integer.MAX_VALUE) {
                nums[lengthCount] = Integer.MAX_VALUE;
                lengthCount++;
            }
        }

        return lengthCount;

    }
}
