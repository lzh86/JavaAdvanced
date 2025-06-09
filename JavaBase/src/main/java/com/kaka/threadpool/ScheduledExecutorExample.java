package com.kaka.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorExample {
    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 6; i++) {
            fixDelayTask(i);
        }
    }


    private static void fixDelayTask(int i) {
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                String str = "I am a task, which submited by the so called laoda, and run by those anonymous workers";
                System.out.println(i + "  " + str);

            }
        }, 0L, 5L, TimeUnit.SECONDS);
    }

    public static void delayTask() throws ExecutionException, InterruptedException {
        ScheduledFuture<String> schedule = executorService.schedule(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "I am a task, which submited by the so called laoda, and run by those anonymous workers";
            }
        }, 2, TimeUnit.SECONDS);
        System.out.println(schedule.get());
    }


}
