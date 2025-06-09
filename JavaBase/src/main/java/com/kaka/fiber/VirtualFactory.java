package com.kaka.fiber;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.TimeUnit;

public class VirtualFactory {
    public static void main(String[] args) {
        ForkJoinPool.ForkJoinWorkerThreadFactory forkJoinWorkerThreadFactory = new ForkJoinPool.ForkJoinWorkerThreadFactory() {
            @Override
            public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
                //return new CarrierThread(pool);
                return null;
            }

            // newThread这个调用链上的所有类和初始化都需要用户保证遇到pin问题前全部加载和初始化。
            // 用户可以选择在运行任务前主动触发一次newThread。如果newThread中有选择逻辑，用户需要保证路径全覆盖。
            // 这里的newThread最佳使用jdk.internal.misc.CarrierThread，否则无法在遇到pinned问题时生成多余线程。
            // 当然，使用其余的普通线程编码上并不会出现问题。
        };

        ForkJoinPool scheduler = new ForkJoinPool(4, forkJoinWorkerThreadFactory, (t, e) -> {
        }, true, 4, 10, 1, pool -> true, 30, TimeUnit.SECONDS);
        // 4 parallelism, 4 corePoolSize, 10 maximumPoolSize, 1 minimumRunnable, 30s keepAliveTime
        Thread.Builder builder = virtualThreadBuilder(scheduler);
        // 单个任务用法和Thread.ofVirtual()一致
        builder.start(() -> {
        });
        // es用法
        ExecutorService es = Executors.newThreadPerTaskExecutor(builder.factory());
    }

    // java.lang.ThreadBuilders$VirtualThreadBuilder(Executor) 本身被JDK中标记成只为测试使用的方法，需要反射
    public static Thread.Builder.OfVirtual virtualThreadBuilder(Executor scheduler) {
        try {
            Class<?> clazz = Class.forName("java.lang.ThreadBuilders$VirtualThreadBuilder");
            Constructor<?> ctor = clazz.getDeclaredConstructor(Executor.class);
            ctor.setAccessible(true);
            return (Thread.Builder.OfVirtual) ctor.newInstance(scheduler);
        } catch (
                InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException re) {
                throw re;
            }
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
