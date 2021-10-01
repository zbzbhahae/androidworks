package com.zb.leetcode.leetcode;

import com.zb.leetcode.utils.P;
import com.zb.leetcode.utils.TimeCheck;

/**
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 *
 * 限制：
 *
 * 2 <= n <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class OfferLC003 {

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static void main(String[] args) {
        int[] nums = {1,3,4,5,3,5,6,3,2,1};
        TimeCheck.check("findRepeatNumber", new TimeCheck.CheckCallback() {
            @Override
            public void callFun() {
                P.p(findRepeatNumber(nums) + "");
            }
        });
    }
    /**
     * 原地置换
     * 将nums[i]放到nums[nums[i]]位置去 如果位置已经有该数字 说明重复
     * 比如 将5放到index位置5去，当再次遇到5时，index已经有5，则重复
     */
    public static int findRepeatNumber(int[] nums) {
        for(int i=0; i<nums.length;) {
            if(i != nums[i]) {
                if(nums[nums[i]] == nums[i])
                    return nums[i];
                int tem = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = tem;
            } else {
                i++;
            }
        }


        return -1;
    }
}
