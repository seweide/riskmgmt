package com.haier.hairy.rmp.test;/**
 * @Classname TestInstance
 * @Description TODO
 * @Date 2021/2/3 17:46
 * @Created by Seweide
 */


/**
 * @Classname TestInstance
 * @Description TODO
 * @Date 2021/2/3 17:46
 * @Created by Seweide
 */
public class TestInstance {

    private volatile static TestInstance instance;

    public static TestInstance getInstance(){
        if(instance == null){
            synchronized(TestInstance.class){
                if(instance == null){
                    instance = new TestInstance();
                }
            }
        }
        return instance;
    }
    // instance是volatile变量
//    Singleton volatile instance = new Singleton();

    public static void main(String[] args) {


        TestInstance testInstance = getInstance();
        System.out.println("args = [" + testInstance + "]");
    }

}
