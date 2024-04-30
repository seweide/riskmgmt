package com.haier.hairy.rmp.test;/**
 * @Classname Singleton
 * @Description TODO
 * @Date 2021/2/3 18:04
 * @Created by Seweide
 */

/**
 * 防止重排序
 * @Classname Singleton
 * @Description TODO
 * @Date 2021/2/3 18:04
 * @Created by Seweide
 */
public class Singleton {

    public static volatile Singleton singleton;

    /**
     * 构造函数私有，禁止外部实例化
     */
    private Singleton() {};

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (singleton) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
