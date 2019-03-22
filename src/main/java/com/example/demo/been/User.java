package com.example.demo.been;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    private String uid;
    private String name;

    public User(String uid,String name){
        this.uid = uid;
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }
    public String getName() {
        return name;
    }
}
