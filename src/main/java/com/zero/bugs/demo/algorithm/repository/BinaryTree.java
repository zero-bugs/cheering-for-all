package com.zero.bugs.demo.algorithm.repository;

import java.util.LinkedList;

/**
 * 二叉树
 *
 * @param <T>
 */
public class BinaryTree<T> {

    private BinNode<T> root;


    /**
     * 非递归实现
     *
     * @param values
     */
    public void custructBinaryTree(T[] values) {
        if (values == null || values.length == 0) {
            return;
        }

        root = new BinNode<T>(values[0]);

        LinkedList<BinNode<T>> currentParentNodeList = new LinkedList<BinNode<T>>();
        currentParentNodeList.addFirst(root);
        for (int i = 0; i < values.length / 2; ++i) {
            BinNode<T> leftNode = new BinNode<T>(values[2 * i + 1]);
            BinNode<T> rightNode = null;
            if (2 * i + 2 < values.length) {
                rightNode = new BinNode<T>(values[2 * i + 2]);
            }
            BinNode<T> currentParentNode = currentParentNodeList.pollLast();
            currentParentNode.left = leftNode;
            currentParentNode.right = rightNode;

            currentParentNodeList.addFirst(leftNode);
            if (rightNode != null) {
                currentParentNodeList.addFirst(rightNode);
            }
        }
    }

    /**
     * 构建树 递归实现
     *
     * @param values
     */
    public void custructBinaryTreeIter(T[] values) {
        root = createTreeNode(values, 0);
    }

    private BinNode<T> createTreeNode(T[] values, int index) {

        if (index >= values.length) {
            return null;
        }
        T value = values[index];
        if (value == null) {
            return null;
        }

        BinNode<T> node = new BinNode<T>(values[index]);
        node.left = createTreeNode(values, 2 * index + 1);
        node.right = createTreeNode(values, 2 * index + 2);
        return node;
    }

    /**
     * 前序遍历
     */
    public void preOrderTraversal() {
        preOrderTraversal(root);
        System.out.println("");
    }

    private void preOrderTraversal(BinNode<T> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    /**
     * 中序遍历
     */
    public void midOrderTraversal() {
        midOrderTraversal(root);
        System.out.println("");
    }

    private void midOrderTraversal(BinNode<T> root) {
        if (root == null) {
            return;
        }
        midOrderTraversal(root.left);
        System.out.print(root.value);
        midOrderTraversal(root.right);
    }

    public void backOrderTraversal() {
        backOrderTraversal(root);
        System.out.println("");
    }

    private void backOrderTraversal(BinNode<T> root) {
        if (root == null) {
            return;
        }
        backOrderTraversal(root.left);
        backOrderTraversal(root.right);
        System.out.print(root.value);
    }


    /**
     * 非递归实现
     */
    public void preOrderTraversalNoIter() {
        if (root == null) {
            return;
        }

        LinkedList<BinNode<T>> stack = new LinkedList<BinNode<T>>();
        stack.addFirst(root);
        BinNode<T> left = root.left;
        BinNode<T> right = root.right;

        BinNode<T> current;
        LinkedList<BinNode<T>> path = new LinkedList<BinNode<T>>();
        while (!stack.isEmpty()) {
            current = stack.pollFirst();
            if (current != null) {
                path.addFirst(current);
                stack.addFirst(current.right);
                stack.addFirst(current.left);
            }
        }

        for (BinNode<T> b : path) {
            System.out.print(b.value);
        }
        System.out.println("");
    }

    public void midOderListNoIter() {
        if (root == null) {
            return;
        }

        LinkedList<BinNode<T>> stack = new LinkedList<BinNode<T>>();
        BinNode<T> current = root;
        BinNode<T> tmp;
        LinkedList<BinNode<T>> path = new LinkedList<BinNode<T>>();
        while (current!=null || !stack.isEmpty()) {
            while (current != null) {
                stack.addFirst(current);
                current = current.left;
            }

            tmp = stack.pollFirst();
            path.add(tmp);
            current = tmp.right;
        }

        for (BinNode<T> b : path) {
            System.out.print(b.value);
        }
        System.out.println("");
    }

    /**
     * 右孩子优先的先序遍历序列的逆序列就是左孩子优先的后序遍历序列
     */
    public void backOderTraversalNoIter() {
        if (root == null) {
            return;
        }

        LinkedList<BinNode<T>> stack = new LinkedList<BinNode<T>>();
        stack.addFirst(root);
        BinNode<T> left = root.left;
        BinNode<T> right = root.right;

        BinNode<T> current;
        LinkedList<BinNode<T>> path = new LinkedList<BinNode<T>>();
        while (!stack.isEmpty()) {
            current = stack.pollFirst();
            if (current != null) {
                path.addFirst(current);
                stack.addFirst(current.left);
                stack.addFirst(current.right);
            }
        }

        for (BinNode<T> b : path) {
            System.out.print(b.value);
        }
        System.out.println("");
    }

    /**
     * 指定父节点插入子节点
     *
     * @param parent
     * @param value
     * @param leftChild
     */
    public void insert(BinNode<T> parent, T value, boolean leftChild) {

    }


    static class BinNode<T> {
        T value;
        BinNode<T> left;
        BinNode<T> right;

        public BinNode(T value) {
            this.value = value;
        }
    }
}


