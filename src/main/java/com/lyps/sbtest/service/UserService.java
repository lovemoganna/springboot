package com.lyps.sbtest.service;

import com.lyps.sbtest.dao.UserDao;
import com.lyps.sbtest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IDEA
 * @author:LiGang
 * Date:2018/1/18
 * Time:11:35
 */
@Service
public class UserService {
    @Autowired
    /**
     * 注入Spring容器中的Bean对象
     */
    private UserDao userDao;

    /**
     * 调用UserDao中的方法进行查询
     * @return List<User>
     */

    public List<User> queryUserList(){
        return this.userDao.queryUserList();
    }
}
