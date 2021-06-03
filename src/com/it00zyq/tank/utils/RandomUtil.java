package com.it00zyq.tank.utils;

import java.util.Random;

/**
 * @author IT00ZYQ
 * @date 2021/6/3 10:44
 **/
public class RandomUtil {
    private static final Random RANDOM = new Random();

    public static int randomX() {
        return RANDOM.nextInt(Constant.FRAME_WIDTH);
    }

    public static int randomY() {
        return RANDOM.nextInt(Constant.FRAME_HEIGHT);
    }


}
