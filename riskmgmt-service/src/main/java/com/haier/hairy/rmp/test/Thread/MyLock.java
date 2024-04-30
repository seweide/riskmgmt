package com.haier.hairy.rmp.test.Thread;

/**
 * //定义两把钥匙，也就是定义锁对象，把它们定义为静态的，可以直接类名调用
 * @Classname MyLock
 * @Description TODO
 * @Date 2021/2/2 14:20
 * @Created by Seweide
 */
public interface MyLock {
    //定义两把钥匙，也就是定义锁对象，把它们定义为静态的，可以直接类名调用
    public static final Object key1 = new Object() ;
    public static final Object key2 = new Object() ;
}
