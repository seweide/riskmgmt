package com.haier.hairy.rmp.test.Thread;/**
 * @Classname Demo
 * @Description TODO
 * @Date 2021/2/2 14:22
 * @Created by Seweide
 */

/**
 * @Classname Demo
 * @Description TODO
 * @Date 2021/2/2 14:22
 * @Created by Seweide
 */
public class Demo extends  Thread{
    //定义一个布尔类型来，判断是谁先拿锁，true是1先拿锁，反之是2
    boolean flag;

    public Demo(boolean flag){
        this.flag=flag;
    }

    @Override
    public void run() {
        if(flag){
            //嵌套同步代码块
            synchronized (MyLock.key1){
                System.out.println("门开了1");
                synchronized (MyLock.key2){
                    System.out.println("门开了2");
                }
            }
        }else {
            synchronized (MyLock.key2){
                System.out.println("门开了2");
                synchronized (MyLock.key1){
                    System.out.println("门开了1");
                }
            }
        }

    }

}
