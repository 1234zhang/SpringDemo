package com.example.demo.service.serviceImp;

import com.example.demo.been.Role;
import com.example.demo.been.User;
import com.example.demo.dao.daoImp.UserDaoImp;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserServiceImp implements UserService {
    private final UserDaoImp userDaoImp;
    @Autowired
    public UserServiceImp(UserDaoImp userDaoImp){
        this.userDaoImp = userDaoImp;
    }
    @Override
    public Map<String, List<Role>> getRoles(String name) {
        User user = userDaoImp.getUser(name);
        if(user == null){
            return null;
        }
        Map<String, List<Role>> roleMap = new HashMap<>();
        List<Role> roles = new ArrayList<>();
        List<String> rids = userDaoImp.matchPrivilege(user.getUid());
        for (String rid : rids) {
            roles.addAll(userDaoImp.matchRole(rid));
        }
        roleMap.put(user.getName(), roles);
        return roleMap;
    }
    public User getUser(String name){
        User user = userDaoImp.getUser(name);
        return user;
    }
}
