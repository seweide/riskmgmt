package com.haier.hairy.rmp.test.Thread;/**
 * @Classname MyDemo
 * @Description TODO
 * @Date 2021/2/2 14:44
 * @Created by Seweide
 */

/**
 * @Classname MyDemo
 * @Description TODO
 * @Date 2021/2/2 14:44
 * @Created by Seweide
 */
public class MyDemo implements Runnable{

    @Override
    public void run() {
        //通过查看线程名字，看一共有多少线程在执行任务
        System.out.println(Thread.currentThread().getName()+"正在执行程序");
    }
}
