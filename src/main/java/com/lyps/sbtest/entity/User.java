package com.lyps.sbtest.entity;

/**
 * Created with IDEA
 * @author:LiGang
 * Date:2018/1/18
 * Time:11:27
 */
public class User {
    private String username;
    private String password;

    private Integer age ;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
