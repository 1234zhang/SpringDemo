package com.example.demo.dao.daoImp;

import com.example.demo.been.Privilege;
import com.example.demo.been.Role;
import com.example.demo.been.User;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserDaoImp(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Role> matchRole(String rid) {
        String sql = "select * from role where rid = ?";
        List<Role> roles = new ArrayList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql,rid);
        while(rs.next()){
           roles.add( new Role(rs.getString("rid"),rs.getString("rolename")));
        }
        return roles;
    }

    @Override
    public List<String> matchPrivilege(String uid) {
        String sql = "select * from privilege where uid = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql,uid);
        List<String> list = new ArrayList<>();
        while(rs.next()){
            list.add(rs.getString("rid"));
        }
        return list;
    }

    @Override
    public User getUser(String name) {
        String sql = "select * from user where name = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql,name);
        while(rs.next()){
            return new User(rs.getString("uid"),rs.getString("name"));
        }
        return null;
    }
}
