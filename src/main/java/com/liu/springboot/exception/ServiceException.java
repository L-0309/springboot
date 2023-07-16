package com.liu.springboot.exception;

import lombok.Getter;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-06-14  00:25
 * @Description: 自定义异常
 * @Author: LiuHaoYu
 */
@Getter
public class ServiceException extends RuntimeException{
    private final String code;

    public ServiceException(String code, String msg){
        super(msg);
        this.code = code;
    }
}
