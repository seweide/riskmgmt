package com.haier.hairy.rmp.test.Tree;/**
 * @Classname Remve
 * @Description TODO
 * @Date 2021/2/1 17:30
 * @Created by Seweide
 */

/**
 * @Classname Remve
 * @Description TODO
 * @Date 2021/2/1 17:30
 * @Created by Seweide
 */
public class Remve {

//    private NodeTest root;
//
//    public boolean remove(int val){
//        NodeTest node = getNode(val);
//        if(node == null){
//            return false;
//        }
//        if(node.leftChild == null){// 1、左节点不存在，右节点可能存在，包含两种情况  ，两个节点都不存在和只存在右节点
//            transplant(node, node.rightChild);
//        }else if(node.rightChild == null){//2、左孩子存在，右节点不存在
//            transplant(node, node.leftChild);
//        }else{// 3、两个节点都存在
//            NodeTest successor = getSuccessor(node);// 得到node后继节点
//            if(successor.parent != node){// 后继节点存在node的右子树中。
//                transplant(successor, successor.rightChild);// 用后继节点的右子节点替换该后继节点
//                successor.rightChild = node.rightChild;// 将node节点的右子树赋给后继节点的右节点，即类似后继与node节点调换位置
//                successor.rightChild.parent = successor;// 接着上一步  给接过来的右节点的父引用复制
//            }
//            transplant(node, successor);
//            successor.leftChild = node.leftChild;
//            successor.leftChild.parent = successor;
//        }
//        return true;
//    }
//    /**
//     * 将child节点替换node节点
//     * @param root    根节点
//     * @param node    要删除的节点
//     * @param child   node节点的子节点
//     */
//    private void transplant(NodeTest node,NodeTest child){
//        /**
//         * 1、先判断 node是否存在父节点
//         *    1、不存在，则child替换为根节点
//         *    2、存在，则继续下一步
//         * 2、判断node节点是父节点的那个孩子(即判断出 node是右节点还是左节点)，
//         *    得出结果后，将child节点替换node节点 ，即若node节点是左节点 则child替换后 也为左节点，否则为右节点
//         * 3、将node节点的父节点置为child节点的父节点
//         */
//
//        if(node.parent == null){
//            this.root = child;
//        }else if(node.parent.leftChild == node){
//            node.parent.leftChild = child;
//        }else if(node.parent.rightChild == node){
//            node.parent.rightChild = child;
//        }
//        if(child != null){
//            child.parent = node.parent;
//        }
//    }
}
