package com.haier.hairy.rmp.test.Thread;/**
 * @Classname ThreadPoolExecutorTask
 * @Description TODO
 * @Date 2021/2/4 16:32
 * @Created by Seweide
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.TimeUnit;

/**
 * @Classname ThreadPoolExecutorTask
 * @Description TODO
 * @Date 2021/2/4 16:32
 * @Created by Seweide
 */
public class ThreadPoolExecutorTask {

//    public ThreadPoolExecutor(int corePoolSize,
//                              int maximumPoolSize,
//                              long keepAliveTime,
//                              TimeUnit unit,
//                              BlockingQueue<Runnable> workQueue,
//                              RejectedExecutionHandler handler)

//    public void execute(Runnable command) {
//        if (command == null)
//            throw new NullPointerException();//如果线程数大于等于基本线程数或者线程创建失败，将任务加入队列
//        if (poolSize >= corePoolSize || !addIfUnderCorePoolSize(command)) {//线程池处于运行状态并且加入队列成功
//            if (runState == RUNNING && workQueue.offer(command)) {
//                if (runState != RUNNING || poolSize == 0)
//                    ensureQueuedTaskHandled(command);
//            }//线程池不处于运行状态或者加入队列失败，则创建线程（创建的是非核心线程）
//            else if (!addIfUnderMaximumPoolSize(command))//创建线程失败，则采取阻塞处理的方式
//            reject(command); // is shutdown or saturated
//        }
//    }
}
