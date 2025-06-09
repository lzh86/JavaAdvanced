package com.kaka.thread;

public class DaemonThreadExample {
    public static void main(String[] args) {
        // 创建守护线程
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("守护线程运行中...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 设置为守护线程
        daemonThread.setDaemon(true);
        daemonThread.start();

        // 主线程（用户线程）执行完毕后，JVM会立即退出
        System.out.println("主线程结束");
    }
}
