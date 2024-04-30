package com.haier.hairy.rmp.test;/**
 * @Classname TestVolatile
 * @Description TODO
 * @Date 2021/2/3 17:34
 * @Created by Seweide
 */

/**
 * @Classname TestVolatile
 * @Description TODO
 * @Date 2021/2/3 17:34
 * @Created by Seweide
 */
public class TestVolatile {

    int a = 1;
    int b = 2;

    public void change(){
        a = 3;
        b = a;
    }

    public void print(){
        System.out.println("b="+b+";a="+a);
    }

    public static void main(String[] args) {
        //实现可见性
        while (true){
            final TestVolatile test = new TestVolatile();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();

        }
    }
//    int a = 1;
//    boolean status = false;
//
//    //状态切换为true
//    public void changeStatus(){
//        a = 2;   //1
//        status = true;  //2
//    }
//
//    //若状态为true，则为running
//    public void run(){
//        if(status){   //3
//            int b = a + 1;  //4
//            System.out.println(b);
//        }
//    }

}
