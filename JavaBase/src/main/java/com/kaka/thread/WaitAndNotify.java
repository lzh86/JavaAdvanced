package com.kaka.thread;

public class WaitAndNotify {
    public static void main(String[] args) {
        Queue methodClass = new Queue();
        Thread t1 = new Thread(() -> {
            try {
                methodClass.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                methodClass.customer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        Thread t3 = new Thread(() -> {
            try {
                methodClass.customer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3");
        t1.start();
        t2.start();
        t3.start();

    }
}

class Queue {
    // 定义队里最大长度
    private final int MAX_COUNT = 20;

    int productCount = 0;

    public synchronized void product() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
            Thread.sleep(10);
            if (productCount >= MAX_COUNT) {
                System.out.println("队列已满,不再接受入队");

                wait();
            } else {
                productCount++;
            }

            notifyAll();
        }
    }

    public synchronized void customer() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
            Thread.sleep(10);
            if (productCount <= 0) {
                System.out.println("队列已空,无法继续消费");
                wait();
            } else {
                productCount--;
            }

            notifyAll();
        }
    }
}
