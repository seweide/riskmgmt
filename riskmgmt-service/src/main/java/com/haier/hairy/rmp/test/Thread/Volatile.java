package com.haier.hairy.rmp.test.Thread;/**
 * @Classname Volatile
 * @Description TODO
 * @Date 2021/2/2 14:30
 * @Created by Seweide
 */

/**
 * @Classname Volatile
 * @Description TODO
 * @Date 2021/2/2 14:30
 * @Created by Seweide
 */
public class Volatile implements Runnable{

    //定义一个布尔类型，用修改布尔值看这一过程
//    private boolean b=false;

    //volatile 关键字，可以使修改的值，立即写入主存中
    private volatile boolean b=false;

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    @Override
    public void run() {
        try {
            //模拟网络延迟
            Thread.sleep(50);
        } catch (InterruptedException e) {

        }
        //把修改b的值
        b=true;
        //输出b的值
        System.out.println("b="+isB());

    }
}
