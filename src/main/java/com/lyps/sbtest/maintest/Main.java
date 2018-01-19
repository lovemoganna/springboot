package com.lyps.sbtest.maintest;

import com.lyps.sbtest.config.SpringConfig;
import com.lyps.sbtest.entity.User;
import com.lyps.sbtest.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created with IDEA
 *
 * @author:LiGang
 * @Date:2018/1/18
 * @Time:11:47
 */
/**
 * 用来启动Spring容器
 */
public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        /**
         *通过java配置来实例化spring容器
         */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        /**
         * 在spring容器中获取bean对象
         */
        UserService userService = context.getBean(UserService.class);

        /**
         * 调用对象中的方法
         */
        List<User> userList = userService.queryUserList();
        for (User user : userList) {
            System.out.println(user.getUsername() + user.getPassword() + user.getAge());

        }
        /**
         * 销毁该容器
         */
        context.destroy();
    }
}
