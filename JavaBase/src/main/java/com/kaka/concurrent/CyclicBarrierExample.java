package com.kaka.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        int numFriends = 3;
        CyclicBarrier barrier = new CyclicBarrier(numFriends, new Runnable() {
            /**
             * CyclicBarrier 的回调任务是由最后一个到达屏障点的线程执行的。
             * 这个线程可能在其他线程继续执行后续代码时，已经完成了回调输出。
             */
            @Override
            public void run() {
                System.out.println("回调,所有活动全部完成.");
            }
        });

        for (int i = 0; i < numFriends; i++) {
            new Thread(new Friend(barrier, "朋友" + (i + 1))).start();
        }
    }

    static class Friend implements Runnable {
        private CyclicBarrier barrier;
        private String name;
        private Random random = new Random();

        public Friend(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                // 第一阶段：集合去餐厅
                goToRestaurant();

                // 第二阶段：去电影院
                goToCinema();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void goToRestaurant() throws InterruptedException, BrokenBarrierException {
            int time = random.nextInt(5) + 1; // 随机1-5秒
            System.out.println(name + " 出发去集合点，预计需要" + time + "秒。");
            Thread.sleep(time * 1000);
            System.out.println(name + " 到达集合点，等待其他人...");

            int arrivalIndex = barrier.await(); // 等待所有朋友到达
            if (arrivalIndex == 0) {
                System.out.println("\n所有朋友都到达集合点，现在一起去餐厅！\n");
            }
            System.out.println(name + " 开始前往餐厅。");
        }

        private void goToCinema() throws InterruptedException, BrokenBarrierException {
            int time = random.nextInt(5) + 1; // 随机1-5秒
            System.out.println(name + " 在餐厅用餐完毕，准备去电影院，需要" + time + "秒。");
            Thread.sleep(time * 1000);
            System.out.println(name + " 到达电影院，等待其他人...");

            int arrivalIndex = barrier.await(); // 等待所有朋友到达
            if (arrivalIndex == 0) {
                System.out.println("\n所有朋友都到达电影院，开始观影！\n");
            }
            System.out.println(name + " 进入电影院看电影。");
        }
    }
}
