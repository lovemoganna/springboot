Spring-annotation
==

##1.spring注解使用的基本过程
1.一个实体类User

2.一个UserDao

    模拟数据库的操作
    
3.一个UserService

    @Service
    
    @Autowired
     
    private UserDao userDao;
    注入spring容器中的UserDao.
    
    写个方法,就能调用UserDao中的方法去查询数据库
    
4.一个SpringConfig

    用于实例化Spring容器
    
    在类上添加
    
    @Configuration//代表该类是一个Spring的配置,相当于xml
    
    @ComponentScan(basePackages="xxxx")//配置扫描包
    
    
    让Spring容器管理Bean
    
    在方法前面添加
    
    @Bean//通过注解声明一个Bean对象,相当于xml中的<Bean/>
    
5.一个测试类
```
    写个main方法
   
    1.实例化一个Spring容器
      
      实例化AnnotationConfigApplicationContext,从里面加载SpringConfig.class
      
    2.在Spring容器中获取Bean对象
    
     context.getBean(UserService.class);
     
     原因就是在SpringConfig中配置了扫描包.
     它会扫描到UserService所在的包.
     UserService本身就有一个@Service注解,这样它就会被载入Spring容器.通过容器就会拿到Service对象
     调用userservice中的方法就会调用UserDao中的方法.
     在UserService中Dao是通过自动注入@AutoWired来调用的.
     
     自动注入的UserDao对象从 SpringConfig里面的@Bean注解加入到Spring容器
     
     这样我们就可以通过执行userservice的方法来间接执行UserDao的方法了
    
    3.调用对象中的方法
     
    4.销毁容器
     context.destory(); 
      
 ```     
      
##2.读取外部的资源配置文件

通过@PropertySource可以指定读取资源的配置文件,

通过@Value注解获取值

如下所示:

```androiddatabinding
@Configuration//声明该类是一个spring的配置,相当于xml文件
@ComponentScan(backPackages="xxxx")//配置扫描包
//多个属性的写法
@PropertySource(value={"classpath:jdbc.properties","xxx","xxx"})

public class SpringConfig{
    
    @Value("${jdbc.url }")
    private String jdbcUrl;
    
    @Bean//通过注解来声明是一个Bean对象,相当于xml中的<bean>
    
    public UserDao getIUserDao(){
    
        return new UserDao;//直接new对象做演示
    }

}
```   

配置文件不存在:

需要我们配置:
```androiddatabinding
@PropertySource(Value={"classpath:jdbc.properties"},ignoreResourceNotFound=true)
```
