package com.haier.hairy.rmp.test.Thread;/**
 * @Classname MyTest2
 * @Description TODO
 * @Date 2021/2/2 14:48
 * @Created by Seweide
 */

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Classname MyTest2
 * @Description TODO
 * @Date 2021/2/2 14:48
 * @Created by Seweide
 */
public class MyTest2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //获取一个线程对象的线程池
        //里面的核心线程数和线程数都是1，并且工作队列使用的是无界队列。由于是单线程工作，每次只能处理一个任务，所以后面所有的任务都被阻塞在工作队列中，只能一个个任务执行。
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> submit = service.submit(new MyCallable(10));
        Future<Integer> submit2 = service.submit(new MyCallable(100));
        Future<Integer> submit3 = service.submit(new MyCallable(1000));
        System.out.println(submit.get());
        System.out.println(submit2.get());
        System.out.println(submit3.get());
        service.shutdown();
    }

}
