package com.lyps.springboottest;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

/**
 * Created with IDEA
 * @author:LiGang
 * Date:2018/1/18
 * Time:21:41
 */

@RestController
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@Configuration
public class HelloApplication {

    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello world! 你好!";
    }

    /**
     * 自定义消息转换器,
     * 只要在有@Configuration的注解中添加消息转换器@Bean加入到Spring容器,
     * 就会被SpringBoot自动加入到容器中.
     * @return StringHttpMessageConverter
     */
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
        StringHttpMessageConverter converter =new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    /**
     * 访问 http://localhost:8080/hello就能看到结果了
     * @param args
     */
    public static void main(String[] args) {
        //这是简单的启动手段
        //SpringApplication.run(HelloApplication.class,args);

        SpringApplication application = new SpringApplication(HelloApplication.class);
        //这样就关闭了Banner效果
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
