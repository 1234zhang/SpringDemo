package com.example.demo.dao;

import com.example.demo.been.Privilege;
import com.example.demo.been.Role;
import com.example.demo.been.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<Role> matchRole(String rid);
    List<String> matchPrivilege(String uid);
    User getUser(String name);
}
