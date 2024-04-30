package com.haier.hairy.rmp.test.Tree;/**
 * @Classname Delete
 * @Description TODO
 * @Date 2021/2/1 17:25
 * @Created by Seweide
 */

/**
 * @Classname Delete
 * @Description TODO
 * @Date 2021/2/1 17:25
 * @Created by Seweide
 */
public class Delete {

    private NodeTest root;

    public boolean delete(int val){
        NodeTest node = getNode(val);
        if(node == null){
            return false;
        }
        NodeTest parent = node.parent;
        NodeTest leftChild = node.leftChild;
        NodeTest rightChild = node.rightChild;
        //以下所有父节点为空的情况，则表明删除的节点是根节点
        if(leftChild == null && rightChild == null){//没有子节点
            if(parent != null){
                if(parent.leftChild == node){
                    parent.leftChild = null;
                }else if(parent.rightChild == node){
                    parent.rightChild = null;
                }
            }else{//不存在父节点，则表明删除节点为根节点
                root = null;
            }
            node = null;
            return true;
        }else if(leftChild == null && rightChild != null){// 只有右节点
            if(parent != null && parent.val > val){// 存在父节点，且node位置为父节点的左边
                parent.leftChild = rightChild;
            }else if(parent != null && parent.val < val){// 存在父节点，且node位置为父节点的右边
                parent.rightChild = rightChild;
            }else{
                root = rightChild;
            }
            node = null;
            return true;
        }else if(leftChild != null && rightChild == null){// 只有左节点
            if(parent != null && parent.val > val){// 存在父节点，且node位置为父节点的左边
                parent.leftChild = leftChild;
            }else if(parent != null && parent.val < val){// 存在父节点，且node位置为父节点的右边
                parent.rightChild = leftChild;
            }else{
                root = leftChild;
            }
            return true;
        }else if(leftChild != null && rightChild != null){// 两个子节点都存在
            NodeTest successor = getSuccessor(node);// 这种情况，一定存在后继节点
            int temp = successor.val;
            boolean delete = delete(temp);
            if(delete){
                node.val = temp;
            }
            successor = null;
            return true;
        }
        return false;
    }

    /**
     * 找到node节点的后继节点
     * 1、先判断该节点有没有右子树，如果有，则从右节点的左子树中寻找后继节点，没有则进行下一步
     * 2、查找该节点的父节点，若该父节点的右节点等于该节点，则继续寻找父节点，
     *   直至父节点为Null或找到不等于该节点的右节点。
     * 理由，后继节点一定比该节点大，若存在右子树，则后继节点一定存在右子树中，这是第一步的理由
     *      若不存在右子树，则也可能存在该节点的某个祖父节点(即该节点的父节点，或更上层父节点)的右子树中，
     *      对其迭代查找，若有，则返回该节点，没有则返回null
     * @param node
     * @return
     */
    private NodeTest getSuccessor(NodeTest node){
        if(node.rightChild != null){
            NodeTest rightChild = node.rightChild;
            while(rightChild.leftChild != null){
                rightChild = rightChild.leftChild;
            }
            return rightChild;
        }
        NodeTest parent = node.parent;
        while(parent != null && (node == parent.rightChild)){
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    /**
     * 将child节点替换node节点
     * @param root    根节点
     * @param node    要删除的节点
     * @param child   node节点的子节点
     */
    private void transplant(NodeTest node,NodeTest child){
        /**
         * 1、先判断 node是否存在父节点
         *    1、不存在，则child替换为根节点
         *    2、存在，则继续下一步
         * 2、判断node节点是父节点的那个孩子(即判断出 node是右节点还是左节点)，
         *    得出结果后，将child节点替换node节点 ，即若node节点是左节点 则child替换后 也为左节点，否则为右节点
         * 3、将node节点的父节点置为child节点的父节点
         */

        if(node.parent == null){
            this.root = child;
        }else if(node.parent.leftChild == node){
            node.parent.leftChild = child;
        }else if(node.parent.rightChild == node){
            node.parent.rightChild = child;
        }
        if(child != null){
            child.parent = node.parent;
        }
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
