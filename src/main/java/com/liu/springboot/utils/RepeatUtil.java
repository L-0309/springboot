package com.liu.springboot.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-08-01  15:38
 * @Description:
 * @Author: LiuHaoYu
 */
public class RepeatUtil {

    /**
     * list集合去重
     * @param list 传入集合
     * @return {@link List}<{@link Integer}>
     */
    public static List<Integer> repeatUtil(List<Integer> list) {
        ArrayList<Integer> numbersList = new ArrayList<>(list);
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(numbersList);
        return new ArrayList<>(hashSet);
    }
}
