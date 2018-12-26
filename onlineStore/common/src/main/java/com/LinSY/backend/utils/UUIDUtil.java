package com.LinSY.backend.utils;

import java.util.UUID;

/**
 * @ClassName UUIDUtil
 * @Author LinSY
 * @Date 2018/9/26 23:09
 * @Version 1.0
 * @Description
 */

public class UUIDUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getLongUUID(){
        return UUID.randomUUID().toString();
    }

}