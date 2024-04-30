package com.haier.hairy.rmp.test.Tree;/**
 * @Classname Print
 * @Description TODO
 * @Date 2021/2/1 17:36
 * @Created by Seweide
 */

/**
 * @Classname Print
 * @Description TODO
 * @Date 2021/2/1 17:36
 * @Created by Seweide
 */
public class Print {
    private static NodeTest root;

    public void print(){
        print(root);
    }
    private void print(NodeTest root){

        if(root != null){
            // 位置在中间，则中序，若在前面，则为先序，否则为后续
            print(root.leftChild);
            System.out.println(root.val);
            print(root.rightChild);
        }
    }

    public static void main(String[] args) {
        NodeTest parent = new NodeTest(1);
        NodeTest leftChild = new NodeTest(2);
        NodeTest rightChild = new NodeTest(3);
        int val = 10;
        NodeTest root = new NodeTest(parent,leftChild,rightChild,val);
//        this.root = root;
        Print print = new Print();
        print.print(root);
    }

}
