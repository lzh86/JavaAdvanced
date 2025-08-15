package com.kaka.proxy;


import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public void add() throws IOException {
        try {
            System.out.println(String.format("user add %s", LocalTime.now()));
            throw new IOException("主动抛出异常");
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
