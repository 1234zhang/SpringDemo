package com.example.demo.been;

import java.util.*;

/*
* 权限类
* */
public class Privilege {
    private String id;
    private String name;
    private static Map<User,Set<Role>> roles;
    static{
        roles = new HashMap<>();
    }

    public static Map<User, Set<Role>> getRoles() {
        return roles;
    }
}
