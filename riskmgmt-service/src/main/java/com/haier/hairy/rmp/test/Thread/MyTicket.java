package com.haier.hairy.rmp.test.Thread;/**
 * @Classname MyTicket
 * @Description TODO
 * @Date 2021/2/2 14:01
 * @Created by Seweide
 */

/**
 * @Classname MyTicket
 * @Description TODO
 * @Date 2021/2/2 14:01
 * @Created by Seweide
 */
public class MyTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //把Ticket对象放入Thread中可以起到对象共享，这样数据才会同步，如果变量是静态的可直接new Thread对象，因为静态变量是共享的
        Thread thread1 = new Thread(ticket);
        //设置线程名字
        thread1.setName("窗口1");

        Thread thread2 = new Thread(ticket);
        thread2.setName("窗口2");

        Thread thread3 = new Thread(ticket);
        thread3.setName("窗口3");
        //开启线程
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
