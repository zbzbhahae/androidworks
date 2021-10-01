package com.zb.leetcode.leetcode;

import java.util.HashMap;

public class TwoSums {

    public static int[] sum(int[] nums, int target) {
        int length = nums.length;
        int tem = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<length; i++) {
            tem = target - nums[i];
            map.put(tem, i);
        }
        for(int i=0; i<length; i++) {

            if(map.containsKey(i)) {
                Integer j = map.get(nums[i]);
                if(i < j) {
                    int[] result = {i, (int)j};
                    return result;
                } else {
                    int[] result = {(int)j, i};
                    return result;
                }
            }
        }
        return null;
    }
}
