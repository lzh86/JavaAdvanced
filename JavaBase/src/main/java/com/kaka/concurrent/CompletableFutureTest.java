package com.kaka.concurrent;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {
    public static void main(String[] args) throws Exception {
        completableFutureResultDemo();
    }


    public static void completableFutureResultDemo() throws Exception {
        CompletableFuture<Object> objectCompletableFuture = completableFutureDemo();
        objectCompletableFuture.handleAsync((result, exception) -> {
            if (exception != null) {
                System.out.println(Thread.currentThread().getName() + "-异步报错");
                return exception.getCause();
            } else {
                return result;
            }
        }).thenApplyAsync((returnStr) -> {
            System.out.println(Thread.currentThread().getName() + "-" + returnStr);
            return returnStr;
        });

        System.out.println(Thread.currentThread().getName() + "-主线程执行逻辑开始");

        try {
            Thread.currentThread().join(5 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static CompletableFuture<Object> completableFutureDemo() throws Exception {
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "-子线程异步执行逻辑开始---");
            try {
                TimeUnit.SECONDS.sleep(2);
                int i = new Random().nextInt(2) + 1;
                if (i % 2 == 0) {
                    throw new RuntimeException("子线程异步执行报错-");
                } else {
                    return "子线程异步执行逻辑正常结束";
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        return objectCompletableFuture;
    }
}
