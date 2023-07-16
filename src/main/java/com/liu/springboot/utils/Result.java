package com.liu.springboot.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-06-17  15:34
 * @Description: 结果集封装
 * @Author: LiuHaoYu
 */
@Getter
@Setter
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public static Result<?> success(){
        Result<?> result = new Result<>();
        result.setCode(Constants.CODE_200);
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>(data);
        result.setCode(Constants.CODE_200);
        return result;
    }

    public static <T> Result<T> success(String msg, T data){
        Result<T> result = new Result<>(data);
        result.setCode(Constants.CODE_0);
        result.setMsg(msg);
        return result;
    }

    public static Result<?> error(String code,String msg){
        Result<?> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result<?> error(){
        Result<?> result = new Result<>();
        result.setCode(Constants.CODE_500);
        result.setMsg("系统错误");
        return result;
    }
}
