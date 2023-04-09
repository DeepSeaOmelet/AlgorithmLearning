package com.test.jie.learn;

//定义结点
class AvlNode {
    int val;
    AvlNode lchild;//左结点
    AvlNode rchild;//右结点
    int height; //记录结点高度
}

public class AVLTree {
    //计算结点高度
    static int height(AvlNode T) {
        if (T == null) {
            return -1;
        } else {
            return T.height;
        }
    }


    //左左型，右旋操作
    static AvlNode R_Rotate(AvlNode K2) {
        AvlNode K1;

        //进行旋转
        K1 = K2.lchild;
        K2.lchild = K1.rchild;
        K1.rchild = K2;

        //重新结算结点的高度
        K2.height = Math.max(height(K2.lchild), height(K2.rchild)) + 1;
        K1.height = Math.max(height(K1.lchild), height(K1.rchild)) + 1;
        return K1;
    }

    //右右型 左旋
    static AvlNode L_Rotate(AvlNode K2) {
        AvlNode K1;

        K1 = K2.rchild;
        K2.rchild = K1.lchild;
        K1.lchild = K2;

        //重新结算结点的高度
        K2.height = Math.max(height(K2.lchild), height(K2.rchild)) + 1;
        K1.height = Math.max(height(K1.lchild), height(K1.rchild)) + 1;

        return K1;
    }

    //左-右型 进行左旋，再右旋
    static AvlNode LRRotate(AvlNode K1){
        //对左子树左旋
        K1.lchild=L_Rotate(K1.lchild);
        //再进行右旋
        return R_Rotate(K1);
    }
    //右-左型 进行右旋，再左旋
    static AvlNode RLRotate(AvlNode K1){
        //对左子树右旋
        K1.rchild=R_Rotate(K1.rchild);
        //再进行左旋
        return R_Rotate(K1);
    }

    //插入数值操作
    static AvlNode insert(int val, AvlNode T) {
        if (T == null) {
            T = new AvlNode();
            T.val = val;
            T.lchild = T.rchild = null;
        } else if(val < T.val) {
            //向左孩子递归插入
            T.lchild = insert(val, T.lchild);
            //进行调整操作
            //如果左孩子的高度比右孩子大2
            if (height(T.lchild) - height(T.rchild) == 2) {
                //左-左型
                if (val < T.lchild.val) {
                    T = R_Rotate(T);
                } else {
                    //左-右型
                    T = RLRotate(T);
                }
            }
        } else if (val > T.val) {
            T.rchild = insert(val, T.rchild);
            //进行调整
            //右孩子比左孩子高度大2
            if(height(T.rchild) - height(T.lchild) == 2)
                //右-右型
                if (val > T.rchild.val) {
                    T = L_Rotate(T);
                } else {
                    T = LRRotate(T);
                }
        }

        //否则，这个节点已经在书上存在了，我们什么也不做

        //重新计算T的高度
        T.height = Math.max(height(T.lchild), height(T.rchild)) + 1;
        return T;

    }
}
