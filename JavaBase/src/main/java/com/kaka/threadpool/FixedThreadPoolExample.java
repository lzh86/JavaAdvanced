package com.kaka.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {
    private static ExecutorService executorService = Executors.newFixedThreadPool(16);
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            final int no = i;
            executorService.execute(() -> {
                try {
                    System.out.println("start:" + no);
                    Thread.sleep(1000L);
                    System.out.println("end:" + no);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        System.out.println("Main Thread End!");
    }
}
