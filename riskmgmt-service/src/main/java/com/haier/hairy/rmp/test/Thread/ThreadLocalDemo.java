package com.haier.hairy.rmp.test.Thread;/**
 * @Classname ThreadLocalDemo
 * @Description TODO
 * @Date 2021/2/4 14:32
 * @Created by Seweide
 */

/**
 * @Classname ThreadLocalDemo
 * @Description TODO
 * @Date 2021/2/4 14:32
 * @Created by Seweide
 */
public class ThreadLocalDemo {

    static ThreadLocal<Integer> local = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static class MyRunnable implements Runnable{

        @Override
        public void run() {
            for (int i = 0;i<3;i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int value = local.get();
                System.out.println(Thread.currentThread().getName() + ":" + value);
                local.set(value + 1);
            }
        }
    }

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }

//    public void set(Integer value) {
//        Thread t = Thread.currentThread();
//        ThreadLocal.ThreadLocalMap map = getMap(t);
//        if (map != null)
//            map.set(this, value);
//        else
//            createMap(t, value);
//    }
}
