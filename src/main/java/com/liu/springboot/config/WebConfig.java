package com.liu.springboot.config;

import com.liu.springboot.utils.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-06-14  01:08
 * @Description: 请求图片路径
 * @Author: LiuHaoYu
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + Constants.UPLOAD_PATH);
    }
}
