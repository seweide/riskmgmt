package com.haier.hairy.rmp.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

/**
 * 随机数工具类
 * @date 2018/8/3
 * @author mjc
 */
public class RandomUtil {


    /**
     * 获取随机数
     * @return
     */
    public static BigInteger getRandomNumber(){
        int number = 999;
        Random random = new Random();
        int result1 = random.nextInt(number);
        return BigInteger.valueOf(result1);
    }

    /**
     * 获取9个小数位的随机数
     * @return
     */
    public static Double getRandomNumberSmall(){
        float Max = 1, Min = 0.1f;
        Random random = new Random();
        BigDecimal db = new BigDecimal(Math.random() * (Max - Min) + Min);
        double b = db.setScale(9, BigDecimal.ROUND_HALF_UP).doubleValue();
        while (String.valueOf(b).split("\\.")[1].length() < 9) {
            String i = String.valueOf(random.nextInt(9));
            b = Double.parseDouble(String.valueOf(b)+i);
        }
        return b;
    }

    public static void main(String[] args) {
        System.err.println(getRandomNumberSmall());
    }
}
