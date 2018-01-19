package com.lyps.springBootTest;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:LiGang
 * Date:2018/1/18
 * Time:21:41
 */

@RestController
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@Configuration
public class HelloApplication {

    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello world!";
    }

    //访问 http://localhost:8080/hello就能看到结果了
    public static void main(String[] args) {
        //SpringApplication.run(HelloApplication.class,args);

        SpringApplication application = new SpringApplication(HelloApplication.class);
        application.setBannerMode(Banner.Mode.OFF);//这样就关闭了Banner效果
        application.run(args);
    }
}
