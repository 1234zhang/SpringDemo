package com.example.demo.service;

import com.example.demo.been.Role;
import com.example.demo.been.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String, List<Role>> getRoles(String name);
}
