package com.liu.springboot.config;

import com.liu.springboot.config.interceptor.JwtInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-06-18  11:14
 * @Description: web拦截器
 * @Author: LiuHaoYu
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                //拦截所有请求，通过判断token是否合法来决定是否需要登录
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login","/user/register",
                        "**/export","/**/import","/**/exportPath","/files/upload","/images/**","/files/items/**",
                        "/files/detail/**","/article/findAll"
                );
    }

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }
}
