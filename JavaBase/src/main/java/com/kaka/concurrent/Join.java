package com.kaka.concurrent;

public class Join {
    public static void main(String[] args) {
        Object objectLocak = new Object();
        MyThread thread1 = new MyThread("thread1 -- ");
        //objectLocak = thread1;
        thread1.setLock(objectLocak);
        thread1.start();

        synchronized (objectLocak) {  // objectLocak或thread1/this
            for (int i = 0; i < 10; i++) {
                if (i == 2) {
                    try {
                        objectLocak.wait(0);
                        thread1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        }
    }

}


class MyThread extends Thread {

    private String name;
    private Object lock;

    public void setLock(Object lock) {
        this.lock = lock;
    }

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (lock) { // 这里用lock或this，效果不同
            for (int i = 0; i < 10; i++) {
                System.out.println(name + i);
            }
        }

//        synchronized (lock) {
//            // 唤醒主线程
//            lock.notify();
//        }
    }

}
