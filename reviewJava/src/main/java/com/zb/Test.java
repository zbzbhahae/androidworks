package com.zb;



import com.zb.reviewjava.P;

import java.util.*;

public class Test {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();
//        String[] oprations = input.split(";");
//        List<String> list = new ArrayList<>();
//        for(int i=0; i<oprations.length; i++) {
//            if(null == oprations[i] || oprations[i].length() < 2)
//                continue;
//
//        }
        Integer a = -1;
        P.p(Integer.toBinaryString(Integer.MIN_VALUE + 1));
        P.p(Integer.toBinaryString(a));
        P.p(Integer.toBinaryString(a>>1));
        P.p(a >>>31);

    }


}
