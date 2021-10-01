package com.zb.reviewjava.io;

import com.zb.reviewjava.P;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        P.p(Math.round(11.5));
        P.p(Math.round(-11.5));
        test();
    }

    static void test() {
//        Scanner s = new Scanner(System.in);
//        int length = Integer.valueOf(s.nextLine());
//        String input = s.nextLine();
//        String[] numStrs = input.split(" ");
//        int[] nums = new int[length];
//        for(int i=0; i<numStrs.length; i++) {
//            nums[i] = Integer.valueOf(numStrs[i]);
//
//        }


        int[] nums = new int[]{1, 2, 3, 3, 2, 1, 1, 1, 0, 1, 0, 0, 1, 3, 7 ,2,3};

        int count = 0;
        int flag = 0;
        for(int i=0; i<nums.length-1; i++) {
            int diff = nums[i+1] - nums[i];
            if(diff > 0 && flag < 0) {//之前递增，现在后一位没递增尾部的大
                flag = 0;
                count++;
            } else if(diff < 0 && flag > 0) {//之前递减，现在后一位没递减尾部的小
                flag = 0;
                count++;
            } else if(flag == 0) {
                if(diff > 0) {
                    flag = 1;
                } else if(diff < 1) {
                    flag = -1;
                }
            }
        }
        System.out.println("子排序序列个数为:" + (count + 1));
    }
}
