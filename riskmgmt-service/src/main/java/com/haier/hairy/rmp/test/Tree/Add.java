package com.haier.hairy.rmp.test.Tree;/**
 * @Classname Add
 * @Description TODO
 * @Date 2021/2/1 16:40
 * @Created by Seweide
 */


/**
 * @Classname Add
 * @Description TODO
 * @Date 2021/2/1 16:40
 * @Created by Seweide
 */
public class Add {

    private NodeTest root;
    private int size;

    public boolean add(int val){
        if(root == null){
            root = new NodeTest(val);
            size++;
            return true;
        }
        NodeTest node = getAdapterNodeTest(root, val);
        NodeTest newNodeTest = new NodeTest(val);
        if(node.val > val){
            node.leftChild = newNodeTest;
            newNodeTest.parent = node;
        }else if(node.val < val){
            node.rightChild = newNodeTest;
            newNodeTest.parent = node;
        }else{
            // 暂不做处理
        }
        size++;
        return true;
    }

    /**
     * 获取要插入的节点的父节点，该父节点满足以下几种状态之一
     *  1、父节点为子节点
     *  2、插入节点值比父节点小，但父节点没有左子节点
     *  3、插入节点值比父节点大，但父节点没有右子节点
     *  4、插入节点值和父节点相等。
     *  5、父节点为空
     *  如果满足以上5种情况之一，则递归停止。
     * @param node
     * @param val
     * @return
     */
    private NodeTest getAdapterNodeTest(NodeTest node,int val) {
        if (node == null) {
            return node;
        }
        // 往左子树中插入，但没左子树，则返回
        if (node.val > val && node.leftChild == null) {
            return node;
        }
        // 往右子树中插入，但没右子树，也返回
        if (node.val < val && node.rightChild == null) {
            return node;
        }
        // 该节点是叶子节点，则返回
        if (node.leftChild == null && node.rightChild == null) {
            return node;
        }

        if (node.val > val && node.leftChild != null) {
            return getAdapterNodeTest(node.leftChild, val);
        } else if (node.val < val && node.rightChild != null) {
            return getAdapterNodeTest(node.rightChild, val);
        } else {
            return node;
        }
    }
}
