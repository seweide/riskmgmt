package com.haier.hairy.rmp.test.Thread;/**
 * @Classname ThreadPoolTest
 * @Description TODO
 * @Date 2021/2/4 16:51
 * @Created by Seweide
 */

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Classname ThreadPoolTest
 * @Description TODO
 * @Date 2021/2/4 16:51
 * @Created by Seweide
 */
public class ThreadPoolTest implements Runnable{

    @Override
    public void run() {
        try
        {
            Thread.sleep(300);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        LinkedBlockingQueue<Runnable> queue =new LinkedBlockingQueue<Runnable>(5);
        //饱和策略：DiscardPolicy：不处理，丢弃掉
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, queue);
        for (int i = 0; i < 16 ; i++)
        {
            threadPool.execute(new Thread(new ThreadPoolTest(), "Thread".concat(i + "")));
            System.out.println("线程池中活跃的线程数： " + threadPool.getPoolSize());
            if (queue.size() > 0)
            {
                System.out.println("----------------队列中阻塞的线程数" + queue.size());
            }
        }
        threadPool.shutdown();
    }
}
