package com.kaka.proxy;


import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("user add");
    }
}
