package com.haier.hairy.rmp.test.Thread;/**
 * @Classname MyCallable
 * @Description TODO
 * @Date 2021/2/2 14:47
 * @Created by Seweide
 */

import java.util.concurrent.Callable;

/**
 * @Classname MyCallable
 * @Description TODO
 * @Date 2021/2/2 14:47
 * @Created by Seweide
 */
public class MyCallable implements Callable<Integer> {

    int len;
    //用来获取需要累加的数的长度
    public MyCallable(int i) {
        len=i;
    }


    @Override
    public Integer call() throws Exception {
        //做累加操作
        int sum=0;
        for (int i = 1; i <= len; i++) {
            sum+=i;
        }
        return sum;
    }
}
