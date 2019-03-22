package com.example.demo.controller;

import com.example.demo.been.Role;
import com.example.demo.been.User;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.daoImp.UserDaoImp;
import com.example.demo.service.serviceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
public class TestController {
    private UserServiceImp userServiceImp;
    private UserDaoImp userDaoImp;
    @Autowired
    public TestController(UserServiceImp userServiceImp, UserDaoImp userDaoImp){
        this.userServiceImp = userServiceImp;
        this.userDaoImp = userDaoImp;
    }
    @GetMapping("/test")
    public String test(String name){
        System.out.println(name);
        return "hello," + name;
    }
    @GetMapping("/rbacTest")
    public String rbacTest(String name){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "来了，老弟! " + name;
    }
}
