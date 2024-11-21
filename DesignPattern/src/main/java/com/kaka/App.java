package com.kaka;

import com.kaka.strgegy.ConvertService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
@SpringBootApplication
public class App {
    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 8, 5000L,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5000), new ThreadPoolExecutor.CallerRunsPolicy());


    public static void main(String[] args) {
        System.out.println("Hello World!");
        ApplicationContext applicationContext = SpringApplication.run(App.class, args);
        Map<String, ConvertService> beansOfType = applicationContext.getBeansOfType(ConvertService.class);
        for (Map.Entry<String, ConvertService> entry : beansOfType.entrySet()) {
            ConvertService convertService = entry.getValue();
            String convert = convertService.convert("123");
            System.out.println(convert);
        }

    }
}
