package com.kaka;

import com.kaka.spi.DataBaseSPI;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;
import java.util.ServiceLoader;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Spring SPI
        List<DataBaseSPI> dataBaseSPIs = SpringFactoriesLoader.loadFactories(DataBaseSPI.class, Thread.currentThread().getContextClassLoader());

        for (DataBaseSPI datBaseSPI : dataBaseSPIs) {
            datBaseSPI.getConnection();
        }

        //JDK SPI
//        ServiceLoader<DataBaseSPI> JDKSPs = ServiceLoader.load(DataBaseSPI.class);
//        for (DataBaseSPI datBaseSPI : JDKSPs) {
//            datBaseSPI.getConnection();
//        }



    }
}
