package com.kaka.spi.impl;

import com.kaka.spi.DataBaseSPI;

public class DB2DataBase implements DataBaseSPI {
    @Override
    public void getConnection() {
        System.out.println("this database is db2");
    }
}
