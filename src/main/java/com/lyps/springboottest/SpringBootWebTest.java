package com.lyps.springboottest;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author: ligang
 * date: 2018/1/19
 * time: 21:39
 * 这是一个配置类的标志
 */
@Configuration
public class  SpringBootWebTest extends WebMvcConfigurerAdapter {
    /**
     * 自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor handlerInterceptor = new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                System.out.println("自定义拦截器...........");
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

            }
        };
        //把自定义的拦截器加入到拦截器的列表当中,并且制定进入拦截器的规则.
        registry.addInterceptor(handlerInterceptor).addPathPatterns("/**");
    }

    /**
     * 自定义消息转换器的第二种方法
     */
    public void messageConverters(List<HttpMessageConverter> httpMessageConverterList){
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        httpMessageConverterList.add(converter);
    }
}
