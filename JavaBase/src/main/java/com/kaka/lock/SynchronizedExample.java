package com.kaka.lock;

public class SynchronizedExample {
    private byte[] lock1 = new byte[1];
    private byte[] lock2 = new byte[1];

    public int num = 0;

    public void add() {
        synchronized (lock1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                num += 1;
            }
            System.out.println(Thread.currentThread().getName() + "_" + num);
        }
    }

    public void lockMethod() {
        synchronized (lock2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1) {
                num += 1;
            }
            System.out.println(Thread.currentThread().getName() + "_" + num);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample synchronizedExample = new SynchronizedExample();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronizedExample.add();
                }
            }).start();
        }


        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronizedExample.lockMethod();
                }
            }).start();
        }

    }
}
