package com.lyps.sbtest.config;

import com.jolbox.bonecp.BoneCPDataSource;
import com.lyps.sbtest.dao.UserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * Created with IDEA
 * @author:LiGang
 * Date:2018/1/18
 * Time:11:39
 */
//主要是用来实例化Spring容器,这就是一个配置类

//通过该类注解来表明该类是一个spring的配置,相当于一个xml的文件
@Configuration
//配置扫描包
@ComponentScan(basePackages = "com.lyps.sbtest")
@PropertySource(value = { "classpath:jdbc.properties" }, ignoreResourceNotFound = true)
public class SpringConfig {
    /**
     * 通过注解来表明是一个Bean对象,相当于xml里面的<bean>
     * @return UserDao
     */
    @Bean
    public UserDao getUserDao(){
        //直接new对象做演示
        return new UserDao();
    }


    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.driverClassName}")
    private String jdbcDriverClassName;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean
    public DataSource dataSource() {
        BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
        // 数据库驱动
        boneCPDataSource.setDriverClass(jdbcDriverClassName);
        // 相应驱动的jdbcUrl
        boneCPDataSource.setJdbcUrl(jdbcUrl);
        // 数据库的用户名
        boneCPDataSource.setUsername(jdbcUsername);
        // 数据库的密码
        boneCPDataSource.setPassword(jdbcUsername);
        // 检查数据库连接池中空闲连接的间隔时间，单位是分，默认值：240，如果要取消则设置为0
        boneCPDataSource.setIdleConnectionTestPeriodInMinutes(60);
        // 连接池中未使用的链接最大存活时间，单位是分，默认值：60，如果要永远存活设置为0
        boneCPDataSource.setIdleMaxAgeInMinutes(30);
        // 每个分区最大的连接数
        boneCPDataSource.setMaxConnectionsPerPartition(100);
        // 每个分区最小的连接数
        boneCPDataSource.setMinConnectionsPerPartition(5);
        return boneCPDataSource;
    }
}
