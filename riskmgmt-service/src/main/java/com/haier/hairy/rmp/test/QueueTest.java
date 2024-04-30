package com.haier.hairy.rmp.test;/**
 * @Classname QueueTest
 * @Description TODO
 * @Date 2021/2/1 11:40
 * @Created by Seweide
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Classname QueueTest
 * @Description TODO
 * @Date 2021/2/1 11:40
 * @Created by Seweide
 */
public class QueueTest {

    public static void main(String[] args) {
        //创建队列实例
        Queue<String> stringQueue = new LinkedList<>();
        //添加队列内容
        stringQueue.offer("a");
        stringQueue.offer("b");
        stringQueue.offer("c");
        stringQueue.offer("d");
        stringQueue.offer("e");
        //打印初始化队列内容
        stringQueue.forEach(s -> {
            System.out.println("打印初始化队列内容 = [" + s + "]");
        });
        System.out.println("=====");
        //返回第一个元素，并在队列中删除
        System.out.println("poll = [" + stringQueue.poll() + "]");
        stringQueue.forEach(s -> {
            System.out.println("打印队列内容 = [" + s + "]");
        });
        System.out.println("========");
        //返回第一个元素
        System.out.println("element = [" + stringQueue.element() + "]");
        stringQueue.forEach(s -> {
            System.out.println("打印队列内容 = [" + s + "]");
        });
        System.out.println("========");
        //返回第一个元素

        System.out.println("peek = [" + stringQueue.peek() + "]");
    }
}
