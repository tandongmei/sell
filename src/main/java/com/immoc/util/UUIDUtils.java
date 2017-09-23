package com.immoc.util;

import java.util.Random;

/**
 * Created by Administrator on 2017/9/10 0010.
 */
public class UUIDUtils {

    public static synchronized String getUUID(){
        Random random = new Random();
        return System.currentTimeMillis()+ String.valueOf(random.nextInt(9000));
    }

}
