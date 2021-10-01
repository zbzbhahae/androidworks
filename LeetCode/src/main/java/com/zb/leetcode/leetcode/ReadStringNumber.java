package com.zb.leetcode.leetcode;

import com.zb.leetcode.utils.P;

/**
 * https://leetcode-cn.com/problems/string-to-integer-atoi
 */
public class ReadStringNumber {

    public static void main(String args[]) {
        P.p("" + myAtoi("-91283472332"));
    }
    public static  int myAtoi1(String s) {
        int result = 0;

        boolean isNegative = false;
        boolean canReadNumber = true;
        boolean outOfbouns = false;
        int count = 0;
        char[] nums = s.toCharArray();
        for(int i=nums.length -  1; i>=0; i--) {
            char tem = nums[i];
            if(tem == ' ') {
                continue;
            } else if(tem == '+') {
                continue;
            } else if(tem == '-') {
                isNegative = true;
                break;
            }

            int temNum = tem - '0';
            if(temNum > 9 || temNum < 0)
                continue;

            if((Integer.MAX_VALUE - result) < (temNum * (10^count))) {
                canReadNumber = false;
                outOfbouns = true;
            }
            if(canReadNumber) {
                int ttem = (int) (temNum * Math.pow(10, count));
                result += ttem;
                count++;
            }
        }

        if(outOfbouns) {
            if(isNegative)
                return Integer.MIN_VALUE;
            return Integer.MAX_VALUE;
        }
        if(isNegative)
            result = -result;


        return result;
    }

    public static  int myAtoi(String s) {
        int result = 0;
        boolean isNegative = false;
        boolean isPositive = false;
        int[] nums = new int[s.length()];
        int intIndex = 0;
//        StringBuilder str = new StringBuilder();
        char[] strChar = s.toCharArray();
        for(int i=0; i<strChar.length; i++) {
            if(strChar[i] == '-') {
                if(isNegative || isPositive)
                    break;
                if(intIndex > 0)
                    break;
                isNegative = true;
                continue;
            } else if(strChar[i] == ' ') {
                if(isNegative || isPositive)
                    break;
                if(intIndex > 0)
                    break;
                continue;
            } else if(strChar[i] == '+') {
                if(isNegative || isPositive)
                    break;
                if(intIndex > 0)
                    break;
                isPositive = true;
                continue;
            }
            int temNum = strChar[i] - '0';
            if(temNum < 0 || temNum > 9)
                break;


//            str.append(temNum);
            nums[intIndex] = temNum;
            intIndex++;
        }


        boolean canReadNumber = true;
        boolean outOfbouns = false;
        int count = 0;
        for(int i=intIndex - 1; i>=0; i--) {
            int temNum = nums[i];

            if((Integer.MAX_VALUE - result) < (temNum * Math.pow(10, count))) {
                canReadNumber = false;
                outOfbouns = true;
            }
            if(canReadNumber) {
                int ttem = (int) (temNum * Math.pow(10, count));
                result += ttem;
                count++;
            }
        }

        if(outOfbouns) {
            if(isNegative)
                return Integer.MIN_VALUE;
            return Integer.MAX_VALUE;
        }
        if(isNegative)
            result = -result;

        return result;
    }
}
