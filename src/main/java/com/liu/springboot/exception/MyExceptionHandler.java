package com.liu.springboot.exception;

import com.liu.springboot.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-06-14  00:24
 * @Description: 异常处理
 * @Author: LiuHaoYu
 */
@ControllerAdvice
public class MyExceptionHandler {

    /**
     * 如果抛出的是ServiceException异常，则调用改方法
     * @param se 业务异常
     * @return {@link Result}<{@link ?}>
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result<?> handle(ServiceException se){
        return Result.error(se.getCode(), se.getMessage());
    }
}
