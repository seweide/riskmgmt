package com.haier.hairy.rmp.test.Tree;/**
 * @Classname NodeTest
 * @Description TODO
 * @Date 2021/2/1 16:27
 * @Created by Seweide
 */

/**
 * 二叉树Demo
 * @Classname NodeTest
 * @Description TODO
 * @Date 2021/2/1 16:27
 * @Created by Seweide
 */
public class NodeTest {
    private NodeTest root;
    //节点父级;
    NodeTest parent;
    //    节点左子;
    NodeTest leftChild;
    //    节点右子;
    NodeTest rightChild;
    int val;

    public NodeTest(NodeTest parent, NodeTest leftChild, NodeTest rightChild,int val) {
        super();
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.val = val;
    }

    public NodeTest(int val){
        this(null,null,null,val);
    }

    public NodeTest(NodeTest node,int val){
        this(node,null,null,val);
    }

    public NodeTest getNode(int val){
        NodeTest temp = root;
        int t;
        do{
            t = temp.val-val;
            if(t > 0){
                temp = temp.leftChild;
            }else if(t < 0){
                temp = temp.rightChild;
            }else{
                return temp;
            }
        }while(temp != null);
        return null;
    }
}
