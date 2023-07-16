package com.liu.springboot.utils;


/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-06-12  17:00
 * @Description: 校验类
 * @Author: LiuHaoYu
 */
public class VerifyUtil {
    /**
     * 校验字符串是否为空
     * @param str 传入字符串
     * @return boolean
     */
    public static boolean verifyStr(String str){
        return !"".equals(str) && str != null;
    }

    /**
     * 校验数字是否为空
     * @param num 传入数字
     * @return boolean
     */
    public static boolean verifyNum(Integer num){
        return num != null && num >= 1;
    }

}
