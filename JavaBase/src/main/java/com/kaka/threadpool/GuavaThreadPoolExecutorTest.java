package com.kaka.threadpool;

import com.google.common.util.concurrent.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GuavaThreadPoolExecutorTest {
    public static ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    public static void main(String[] args) throws Exception {

        ListenableFuture<String> listenableFuture = doSuccess();
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(Thread.currentThread().getName() + "-子线程执行成功:" + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(Thread.currentThread().getName() + "-子线程执行失败");
                t.printStackTrace();
            }
        }, executor);
        System.out.println(Thread.currentThread().getName() + "-主线程执行逻辑");
        Thread.currentThread().join();
    }

    public static ListenableFuture<String> doSuccess() throws Exception {
        ListenableFuture<String> listenableFuture = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "-子线程开始准备执行");
            TimeUnit.SECONDS.sleep(5);
            return "success";
        });
        return listenableFuture;
    }

    public static ListenableFuture<String> doFail() throws Exception {
        ListenableFuture<String> listenableFuture = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "-子线程开始准备执行");
            TimeUnit.SECONDS.sleep(5);
            throw new Exception("error");
        });
        return listenableFuture;
    }

}
