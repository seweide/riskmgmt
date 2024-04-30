package com.haier.hairy.rmp.test.Thread;/**
 * @Classname Time
 * @Description TODO
 * @Date 2021/2/2 14:55
 * @Created by Seweide
 */

import java.util.Timer;
import java.util.TimerTask;

/**
 * 继承TimerTask，需要重新rum方法
 * @Classname Time
 * @Description TODO
 * @Date 2021/2/2 14:55
 * @Created by Seweide
 */
public class Time extends  TimerTask {

    //定义一个Timer对象，到时候可以用它来调用cancel方法
    Timer time;
    //通过构造器给Timer对象赋值
    public Time(Timer time){
        this.time=time;
    }

    @Override
    public void run() {
        System.out.println("爆炸啦");
       //取消定时器
        time.cancel();
    }
}
