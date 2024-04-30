package com.haier.hairy.rmp.test.Thread;/**
 * @Classname Ticket
 * @Description TODO
 * @Date 2021/2/2 11:13
 * @Created by Seweide
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname Ticket
 * @Description TODO
 * @Date 2021/2/2 11:13
 * @Created by Seweide
 */
public class Ticket implements Runnable{

    //设置票数
    int ticket = 100 ;

    //创建一个锁对象，你可以随便new，锁对象:就是一个任意对象
    Object obj=new Object();

    Lock lock = new ReentrantLock();


    @Override
    public void run() {
        //1,原始多线程使用
//        while (true) {
//            if (ticket > 0) {
//                try {
//                    //模拟网络延迟
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + "正在出售" + (ticket--) + "张票");
//            }else {
//                //当票不满足条件是退出循环
//                break;
//            }
//        }
        //2,同步锁
//        while (true) {
//            synchronized (obj) {
//                //线程一旦进入同步代码块,就会持有锁的对象
//                if (ticket > 0) {
//                    try {
//                        Thread.sleep(1000);//模拟网络延迟
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(Thread.currentThread().getName() + "正在出售" + (ticket--) + "张票");
//                }else {
//                    break;
//                }
//            }
//        }
        //3,Lock接口
        while (true) {
            //加锁，进入循环立马上锁，防止另一个线程跟着进入下面的if判断
            lock.lock();
            if (ticket > 0) {
                try {
                    Thread.sleep(1000);//模拟网络延迟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在出售" + (ticket--) + "张票");
                //减完票后，这个线程的任务就该释放锁了，好让下一个线程执行任务
                lock.unlock();
            }else {
                break;
            }
            // }
        }
    }
}
