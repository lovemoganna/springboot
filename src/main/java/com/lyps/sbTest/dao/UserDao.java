package com.lyps.sbTest.dao;

import com.lyps.sbTest.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * author:LiGang
 * Date:2018/1/18
 * Time:11:29
 */
//模拟数据库的查询
public class UserDao {
    public List<User> queryUserList() {
        ArrayList<User> userList = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("username" + i);
            user.setPassword("password" + i);
            user.setAge(i + 1);
            userList.add(user);
        }
        return userList;
    }
}
