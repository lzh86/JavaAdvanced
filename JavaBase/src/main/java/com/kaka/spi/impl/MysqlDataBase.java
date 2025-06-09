package com.kaka.spi.impl;

import com.kaka.spi.DataBaseSPI;

public class MysqlDataBase implements DataBaseSPI {
    @Override
    public void getConnection() {
        System.out.println("this is mysql database");
    }
}
