package com.haier.hairy.rmp.test;/**
 * @Classname CASTest
 * @Description TODO
 * @Date 2021/2/3 10:48
 * @Created by Seweide
 */
import sun.misc.Unsafe;

import java.io.Serializable;
import java.util.concurrent.atomic.*;
/**
 * @Classname CASTest
 * @Description TODO
 * @Date 2021/2/3 10:48
 * @Created by Seweide
 */
public class CASTest extends Number implements Serializable {

    private volatile String vStrA = "A";

    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static long valueoffset = 0;

    static {
        try {
            valueoffset = unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private volatile int value;

//    public final int incrementAndGet() {
//        for (;;) {
//            int current = get();
//            int next = current + 1;
//            if (compareAndSet(current, next))
//                return next;
//        }
//    }
//
//    public final boolean compareAndSet(int expect, int update) {
//        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
//    }

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 6)+"，current data:"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"，current data:"+atomicInteger.get());

    }


    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }
}
