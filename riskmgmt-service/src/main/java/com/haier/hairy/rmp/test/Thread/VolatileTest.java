package com.haier.hairy.rmp.test.Thread;/**
 * @Classname VolatileTest
 * @Description TODO
 * @Date 2021/2/2 14:38
 * @Created by Seweide
 */

/**
 * @Classname VolatileTest
 * @Description TODO
 * @Date 2021/2/2 14:38
 * @Created by Seweide
 */
public class VolatileTest {
    public static void main(String[] args) {
        Volatile v = new Volatile();
        Thread th = new Thread(v);
        th.start();
        while(true){
            //判断v是否修改成功，修改成功就输出进来啦
            if(v.isB()){
                System.out.println("进来啦");
                break;
            }
        }
    }
}
