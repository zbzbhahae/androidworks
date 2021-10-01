package com.zb.reviewjava.thread;

import com.zb.reviewjava.P;

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    public static void main(String[] args) {
        LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                5,
                50,
                15,
                TimeUnit.SECONDS,
                workQueue
        );

        Scanner scanner = new Scanner(System.in);
        while (true) {
             if(scanner.hasNext()) {
                 P.p(scanner.nextLine());
             }
        }
    }
}
