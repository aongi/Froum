package com.forum.domain;

/**
 * 用户实体类
 * Created by Aongi on 2017/5/3.
 * Version 1.0
 */
public class User {
    private int uid;
    private String username;
    private String userpassword;
    private boolean flag;

    public User(int uid, String username, String userpassword, boolean flag) {
        this.uid = uid;
        this.username = username;
        this.userpassword = userpassword;
        this.flag = flag;
    }

    public User() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return uid+"\t \t \t "+username+"\t \t \t "+userpassword+"\t \t \t "+flag;
    }
}
