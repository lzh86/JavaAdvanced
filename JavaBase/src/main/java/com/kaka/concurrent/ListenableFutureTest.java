package com.kaka.concurrent;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ListenableFutureTest {

    private static ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    public static void main(String[] args) throws Exception {

        ListenableFuture<String> listenableFuture = getStringListenableFuture();

        listenableFuture.addListener(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "-子线程返回结果:" + listenableFuture.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, executor);
        System.out.println(Thread.currentThread().getName() + "-主线程继续执行业务逻辑");
        Thread.currentThread().join();
    }

    private static ListenableFuture<String> getStringListenableFuture() {
        ListenableFuture<String> listenableFuture = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "-子线程执行业务逻辑");
            TimeUnit.SECONDS.sleep(5);
            return "子线程执行结束,返回";
        });
        return listenableFuture;
    }
}
