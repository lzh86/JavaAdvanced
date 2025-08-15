package com.kaka;

import com.kaka.spi.DataBaseSPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.retry.annotation.EnableRetry;

import java.util.List;
import java.util.ServiceLoader;

/**
 * Hello world!
 */

@SpringBootApplication
@EnableRetry
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        ApplicationContext applicationContext = SpringApplication.run(App.class, args);

        // Spring SPI
//        List<DataBaseSPI> dataBaseSPIs = SpringFactoriesLoader.loadFactories(DataBaseSPI.class, Thread.currentThread().getContextClassLoader());
//
//        for (DataBaseSPI datBaseSPI : dataBaseSPIs) {
//            datBaseSPI.getConnection();
//        }

        //JDK SPI
//        ServiceLoader<DataBaseSPI> JDKSPs = ServiceLoader.load(DataBaseSPI.class);
//        for (DataBaseSPI datBaseSPI : JDKSPs) {
//            datBaseSPI.getConnection();
//        }



    }
}
