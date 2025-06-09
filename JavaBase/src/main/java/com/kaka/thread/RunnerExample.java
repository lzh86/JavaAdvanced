package com.kaka.thread;

public class RunnerExample {


    public static class Runner1 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("进入Runner1运行状态——————————" + i);
            }
        }
    }

    public static class Runner2 extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("进入Runner2运行状态——————————" + i);
            }

            boolean result = Thread.currentThread().isInterrupted();

            boolean result1 = Thread.interrupted(); // 重置状态

            boolean result3 = Thread.currentThread().isInterrupted();

            System.out.println("Runner2.run result ===>" + result);
            System.out.println("Runner2.run result1 ===>" + result1);
            System.out.println("Runner2.run result3 ===>" + result3);
        }
    }

    public static void main(String[] args) {
        Runner1 runner1 = new Runner1();
        Thread thread1 = new Thread(runner1);

        Runner2 runner2 = new Runner2();
        Thread thread2 = new Thread(runner2);

        thread1.setName("thread1");
        thread1.start();
        thread2.setName("thread2");
        thread2.start();

        thread2.interrupt();  // i = true

        System.out.println(Thread.activeCount());
        //打印当前线程所在线程组的详细信息
        Thread.currentThread().getThreadGroup().list();
        //获取当前线程所在线程组的父线程组中活跃的子线程组数量
        System.out.println(Thread.currentThread().getThreadGroup().getParent().activeGroupCount());
        Thread.currentThread().getThreadGroup().getParent().list();
    }

}









