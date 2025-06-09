package com.kaka.concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        int N = 8;
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < N; i++)
            new Worker(i, semaphore).start();
    }

    static class Worker extends Thread {
        private int num;
        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();  // 在子线程里控制资源占用
                System.out.println("工人" + this.num + "占用一个窗口...");
                Thread.sleep(2000);
                System.out.println("工人" + this.num + "释放出窗口");
                semaphore.release();   // 在子线程里控制释放资源占用
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
