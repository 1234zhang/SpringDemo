package com.example.demo.been;

import java.io.Serializable;
import java.util.Set;

/*
* 角色类
* */
public class Role implements Serializable {
    private String rid;
    private String roleName;
    private String description;
    public Role(String rid,String roleName){
        this.rid = rid;
        this.roleName = roleName;
    }

    public String getRid() {
        return rid;
    }

    public String getRoleName() {
        return roleName;
    }
}
