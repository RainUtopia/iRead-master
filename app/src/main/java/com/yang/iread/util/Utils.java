package com.yang.iread.util;

import java.util.Random;

/**
 * @author: jianhong
 * @createDate: 2018/9/10 19:18
 * @description:
 */
public class Utils {
    public static final int IMG_TOTAL_PAGE = 28;

    public static int getRandom() {
        Random random = new Random();
        return random.nextInt(IMG_TOTAL_PAGE);
    }
}
