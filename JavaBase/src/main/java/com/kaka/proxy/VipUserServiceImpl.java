package com.kaka.proxy;

import org.springframework.stereotype.Service;

@Service
public class VipUserServiceImpl implements UserService {

    @Override
    public void add() {
        System.out.println("vip user add");
    }
}
