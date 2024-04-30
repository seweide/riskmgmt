package com.haier.hairy.rmp.test;/**
 * @Classname Test01Volatile
 * @Description TODO
 * @Date 2021/2/3 18:10
 * @Created by Seweide
 */

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 保证原子性
 * @Classname Test01Volatile
 * @Description TODO
 * @Date 2021/2/3 18:10
 * @Created by Seweide
 */
public class Test01Volatile {
    volatile int i;
//    public void addI(){
//        i++;
//    }

    //Synchronized来保证+1操作的原子性。
//    private synchronized void addI(){
//        i++;
//    }

    //AtomicInteger  +1操作的原子性。
    AtomicInteger integer = new AtomicInteger();

    private void addI(){
        integer.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        final  Test01Volatile test01 = new Test01Volatile();
        for (int n = 0; n < 1000; n++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test01.addI();
                }
            }).start();
        }

        Thread.sleep(10000);//等待10秒，保证上面程序执行完成

        System.out.println(test01.i);
    }
}
