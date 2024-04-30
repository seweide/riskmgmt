package com.haier.hairy.rmp.test.Thread;/**
 * @Classname DemoTest
 * @Description TODO
 * @Date 2021/2/2 14:24
 * @Created by Seweide
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Classname DemoTest
 * @Description TODO
 * @Date 2021/2/2 14:24
 * @Created by Seweide
 */
public class DemoTest {
    public static void main(String[] args) {
//        Demo demo1 = new Demo(true);
//        Demo demo2 = new Demo(false);
//        demo1.start();
//        demo2.start();

        //创建一个线程池，里面有两个线程，里面的2就带表线程数，可以随便写
        ExecutorService pool = Executors.newFixedThreadPool(2);
        //创建一个Runnable对象
        MyDemo myDemo = new MyDemo();
        //提交一个 Runnable 任务用于执行
        pool.submit(myDemo);
        pool.submit(myDemo);
        pool.submit(myDemo);
        pool.submit(myDemo);
        //关闭线程池
        pool.shutdown();

    }
}
