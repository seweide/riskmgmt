package com.haier.hairy.rmp.test.Tree;/**
 * @Classname Put
 * @Description TODO
 * @Date 2021/2/1 16:55
 * @Created by Seweide
 */

/**
 * @Classname Put
 * @Description TODO
 * @Date 2021/2/1 16:55
 * @Created by Seweide
 */
public class Put {

    private NodeTest root;
    private int size;

    public boolean put(int val){
        return putVal(root,val);
    }

    private boolean putVal(NodeTest node,int val){
        if(node == null){// 初始化根节点
            node = new NodeTest(val);
            root = node;
            size++;
            return true;
        }
        NodeTest temp = node;
        NodeTest p;
        int t;
        /**
         * 通过do while循环迭代获取最佳节点，
         */
        do{
            p = temp;
            t = temp.val-val;
            if(t > 0){
                temp = temp.leftChild;
            }else if(t < 0){
                temp = temp.rightChild;
            }else{
                temp.val = val;
                return false;
            }
        }while(temp != null);
        NodeTest newNode = new NodeTest(p, val);
        if(t > 0){
            p.leftChild = newNode;
        }else if(t < 0){
            p.rightChild = newNode;
        }
        size++;
        return true;
    }
}
