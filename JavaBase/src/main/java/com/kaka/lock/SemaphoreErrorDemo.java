package com.kaka.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreErrorDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3, true);
        ReentrantLock reentrantLock = new ReentrantLock();
        Thread threadA = new Thread(new MyRunnable(1, semaphore, reentrantLock), "thread-A");
        Thread threadB = new Thread(new MyRunnable(2, semaphore, reentrantLock), "thread-B");
        Thread threadC = new Thread(new MyRunnable(1, semaphore, reentrantLock), "thread-C");
        threadA.start();
        threadB.start();
        threadC.start();
    }
}

class MyRunnable implements Runnable {
    private int n;
    private Semaphore semaphore;
    private ReentrantLock lock;

    public MyRunnable(int n, Semaphore semaphore, ReentrantLock lock) {
        this.n = n;
        this.semaphore = semaphore;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire(n);
            System.out.println("剩余可用许可证: " + semaphore.drainPermits());
            System.out.println(Thread.currentThread().getName() + "执行完成。。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(n);
        }
    }
}
