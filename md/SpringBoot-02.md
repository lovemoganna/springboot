springBoot02
==

##1.Starter Pom的概念

SpringBoot为我们提供了简化企业级开发绝大多数场景的start pom,只要使用了应用场景所需要的start pom,

相关的技术配置将会消除,就可以得到SpringBoot为我们提供的自动配置的Bean.

##2.官方为我们提供的Start Pom


![image.png](http://upload-images.jianshu.io/upload_images/7505161-ef5468cda17b6d09.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image.png](http://upload-images.jianshu.io/upload_images/7505161-d70da79d788e37fe.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image.png](http://upload-images.jianshu.io/upload_images/7505161-913d23e1aaf31551.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

我们只需要关注我们所需要的Start pom就可以了.

##3.xml配置文件

SpringBoot鼓励0 XML配置,但是在实际项目中,这是不可能的.

此时可以通过下面的配置来做
```androiddatabinding
@ImportResource("classpath:some-context.xml","classpath:another-context.xml")

```

##4.日志

springBoot对各种日志框架都做了支持,我们可以通过配置来修改默认的日志的配置.

##5.设置日志级别

在application.properties里面设置就可以了

```androiddatabinding
#debug级别的日志
logging.level.org.springframework=debug
```

##6.SpringBoot的默认配置的原理

SpringBoot在进行SpringApplication对象实例化时会加载META-INF/spring.factories文件,将该配置文件中的配置加载到Spring容器.

spring.factories在F:/Eclipse_E3/.m2/repository/org/springframework/boot/spring-boot/1.5.2.RELEASE/spring-boot-1.5.2.RELEASE-sources.jar!/META-INF/spring.factories:6下可以找到


####1.过程分析:进入SpringApplication,如果类太多,你就只查他的方法,在进入你想进入的方法去看.

 1.先找到它的初始化方法,去寻找Spring工厂的实例(getSpringFactoriesInstance方法)
 2.找Spring工厂的实例需要载入工厂的类型以及类加载器(loadFactoryNames(type,classLoader))
 3.进入上面的LoadFactoryNames方法,就要加载系统配置文件(FACTORIES_RESOURCE_LOCATION)
 
 最终我们发现这个常量就是public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";
 
 本质上加载的就是spring.factories,我们只看几个配置就可以了
 他就是把类转换成对象来调用.
    / 就是结束配置.
 
 ```androiddatabinding
# Initializers
org.springframework.context.ApplicationContextInitializer=\
org.springframework.boot.autoconfigure.SharedMetadataReaderFactoryContextInitializer,\
org.springframework.boot.autoconfigure.logging.AutoConfigurationReportLoggingInitializer

# Application Listeners
org.springframework.context.ApplicationListener=\
org.springframework.boot.autoconfigure.BackgroundPreinitializer

# Auto Configuration Import Listeners
org.springframework.boot.autoconfigure.AutoConfigurationImportListener=\
org.springframework.boot.autoconfigure.condition.ConditionEvaluationReportAutoConfigurationImportListener

# Auto Configuration Import Filters
org.springframework.boot.autoconfigure.AutoConfigurationImportFilter=\
org.springframework.boot.autoconfigure.condition.OnClassCondition

# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.solr.SolrRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration,\

# Failure analyzers

# Template availability providers

```
####2.下面以Redis的自动配置举例:

从上面的spring.factories里面的# Auto Configure一项可以看出

有关Redis的有这2项
```androiddatabinding
org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration,\
```
看一下源码:

![](http://upload-images.jianshu.io/upload_images/7505161-3704886943422075.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

里面存在着一个条件注解,当存在配置的类的情况下,才会实例化该类.

和一个Redis的配置类.

RedisProperties.java
```androiddatabinding
@ConfigurationProperties(prefix = "spring.redis")//去application.properties里面匹配前缀为spring.redis的所有配置.
public class RedisProperties {
	//下面就是默认的配置项
	private int database = 0;
	private String url;
	private String host = "localhost";
	private String password;
	private int port = 6379;
	private boolean ssl;

```

####3.条件注解
```androiddatabinding
@ConditionalOnBean 当容器中有指定的Bean的条件下

@ConditionalOnClass 当类路径下有指定的类的条件下

@ConditionalOnExpression 基于SpELl表达式作为判断条件

@ConditionalOnJava 基于JVM版本作为判断条件

@ConditionalOnJndi 在JNDL存在的条件下查找指定的位置.

@ConditionalOnMissingBean 当容器没有指定Bean的情况下.

@ConditionalOnMissingClass 当类路径下没有指定的类的情况下.

@ConditionalOnNotWebApplication 当项目不是web项目的情况下

@ConditionalOnProperty 指定的属性是否有指定的值

@ConditionalOnResource 类路径是否有指定的值.

@ConditionalOnSingleCandidate 当指定Bean在容器中只有一个,或者指定首选的Bean

@ConditionalOnWebApplication 当前项目是web项目的条件下.
```
###6.SpringBoot的web开发

web开发的自动配置类:

其实就是SpringMVC的视图解析器

org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration

![](http://upload-images.jianshu.io/upload_images/7505161-c4a264d6f22570c6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

它的前缀和后缀都是定义在view中的,没有写死
![](http://upload-images.jianshu.io/upload_images/7505161-de3e985a9075109d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

所以可以在全局配置文件application.properties里面设置
![](http://upload-images.jianshu.io/upload_images/7505161-9e29320d275a70de.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

###7.自动配置静态资源

