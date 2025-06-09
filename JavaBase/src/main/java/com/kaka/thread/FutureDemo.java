package com.kaka.thread;

import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class FutureDemo {

    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 3, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(100));

    public static void main(String[] args) {
        Future<String> submit = poolExecutor.submit(() -> {
            System.out.println(1 + 1);
            return "hello";
        });


        System.out.println("123");

        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
