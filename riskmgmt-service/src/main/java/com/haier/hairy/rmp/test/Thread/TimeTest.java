package com.haier.hairy.rmp.test.Thread;/**
 * @Classname TimeTest
 * @Description TODO
 * @Date 2021/2/2 14:56
 * @Created by Seweide
 */

import java.util.Timer;

/**
 * @Classname TimeTest
 * @Description TODO
 * @Date 2021/2/2 14:56
 * @Created by Seweide
 */
public class TimeTest {

    public static void main(String[] args) {
//        //new一个Timer对象，用来调方法
//        Timer timer = new Timer();
//        //调用Timer对象的方法schedule，第一个参数必须是TimerTask对象，Time继承了它因此也是这个对象，第二个参数表示在2秒后运行run方法，这个参数只有在第一次使用run方法，最后一个参数是每隔1秒，运行一次run方法
//        timer.schedule(new Time(),2000,1000);

        Timer timer = new Timer();
        timer.schedule(new Time(timer),2000,1000);
    }
}
