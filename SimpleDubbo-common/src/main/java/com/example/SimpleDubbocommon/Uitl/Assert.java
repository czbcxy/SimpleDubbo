package com.example.SimpleDubbocommon.Uitl;

import org.springframework.lang.Nullable;

/**
 * @author 贾凯
 * @date 2019/5/13
 * @time 10:39
 * @email
 **/

public class Assert {

    public static void notNull(@Nullable Object object, String message){
        if(object == null){
            throw new IllegalArgumentException(message);
        }
    }
}
