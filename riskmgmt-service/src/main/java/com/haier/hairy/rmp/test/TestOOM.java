package com.haier.hairy.rmp.test;/**
 * @Classname TestOOM
 * @Description TODO
 * @Date 2021/2/4 15:55
 * @Created by Seweide
 */

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname TestOOM
 * @Description TODO
 * @Date 2021/2/4 15:55
 * @Created by Seweide
 */
public class TestOOM {

    private static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {
//        testStrongReference();
//        testSoftReference();
//        testSoftReference1();

        //弱引用
        testWeakReference();

    }
    private static void testStrongReference() {
        // 当 new byte为 1M 时，程序运行正常
        byte[] buff = new byte[1024 * 1024 * 2];
    }

    private static void testSoftReference() {
        for (int i = 0; i < 10; i++) {
            byte[] buff = new byte[1024 * 1024];
            SoftReference<byte[]> sr = new SoftReference<>(buff);
            list.add(sr);
        }

        System.gc(); //主动通知垃圾回收

        for(int i=0; i < list.size(); i++){
            Object obj = ((SoftReference) list.get(i)).get();
            System.out.println(obj);
        }

    }

    private static void testSoftReference1() {
        byte[] buff = null;

        for (int i = 0; i < 10; i++) {
            buff = new byte[1024 * 1024];
            SoftReference<byte[]> sr = new SoftReference<>(buff);
            list.add(sr);
        }

        System.gc(); //主动通知垃圾回收

        for(int i=0; i < list.size(); i++){
            Object obj = ((SoftReference) list.get(i)).get();
            System.out.println(obj);
        }

        System.out.println("buff: " + buff.toString());
    }

    //弱引用
    private static void testWeakReference() {
        for (int i = 0; i < 10; i++) {
            byte[] buff = new byte[1024 * 1024];
            WeakReference<byte[]> sr = new WeakReference<>(buff);
            list.add(sr);
        }

        System.gc(); //主动通知垃圾回收

        for(int i=0; i < list.size(); i++){
            Object obj = ((WeakReference) list.get(i)).get();
            System.out.println(obj);
        }
    }

}
