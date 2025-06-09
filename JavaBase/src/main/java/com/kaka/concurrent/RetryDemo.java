package com.kaka.concurrent;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RetryDemo {

    int i = 1;

    public static void main(String[] args) {
        RetryDemo threadLocalDemo = new RetryDemo();
        threadLocalDemo.test();
    }

    private void test() {
        retry:
        for (; ; ) {
            i++;
            if (i > 3) break retry;
        }
        System.out.println("123");
    }

    public static class CountDownLatchDemo {
        private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 8, 5000L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5000), new ThreadPoolExecutor.CallerRunsPolicy());

        public static void countDownLatchDemo1() throws Exception {
            Map<String, String> map = new HashMap<>();
            map.put("test1", "hello");
            map.put("test2", "hello");

            ArrayList<String> list = new ArrayList<>();
            CountDownLatch countDownLatch = new CountDownLatch(map.size());

            if (StrUtil.isNotBlank(map.get("test1"))) {
                poolExecutor.execute(() -> {
                    list.add("123");
                    countDownLatch.countDown();
                });
            }

            if (StrUtil.isNotBlank(map.get("test2"))) {
                poolExecutor.execute(() -> {
                    try {
                        list.add("456");
                        Thread.sleep(1000 * 10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        countDownLatch.countDown();
                    }
                });
            }

            countDownLatch.await(5, TimeUnit.SECONDS);
            System.out.println(list);
            poolExecutor.shutdown();
        }

        public static void countDownLatchDemo2() throws Exception {
            int N = 3;
            CountDownLatch ready = new CountDownLatch(1);
            CountDownLatch done = new CountDownLatch(N);
            for (int i = 0; i < N; i++) {
                new Thread(() -> {
                    try {
                        ready.await();  // 等待所有线程就绪
                        // 执行模拟请求
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        done.countDown();
                    }
                }).start();
            }
            // 开始测试
            ready.countDown();
            done.await();  // 等到所有线程结束

        }


        public static void main(String[] args) throws Exception {

        }


    }
}
