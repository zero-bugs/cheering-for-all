package com.zero.bugs.demo.algorithm.repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树
 *
 * @param <T>
 */
public class BinaryTree<T> {

    private TreeNode<T> root;


    /**
     * 非递归实现
     *
     * @param values
     */
    public void custructBinaryTree(T[] values) {
        if (values == null || values.length == 0) {
            return;
        }

        root = new TreeNode<T>(values[0]);

        LinkedList<TreeNode<T>> currentParentNodeList = new LinkedList<TreeNode<T>>();
        currentParentNodeList.addFirst(root);
        for (int i = 0; i < values.length / 2; ++i) {
            TreeNode<T> leftNode = new TreeNode<T>(values[2 * i + 1]);
            TreeNode<T> rightNode = null;
            if (2 * i + 2 < values.length) {
                rightNode = new TreeNode<T>(values[2 * i + 2]);
            }
            TreeNode<T> currentParentNode = currentParentNodeList.pollLast();
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

    private TreeNode<T> createTreeNode(T[] values, int index) {

        if (index >= values.length) {
            return null;
        }
        T value = values[index];
        if (value == null) {
            return null;
        }

        TreeNode<T> node = new TreeNode<T>(values[index]);
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

    private void preOrderTraversal(TreeNode<T> root) {
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

    private void midOrderTraversal(TreeNode<T> root) {
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

    private void backOrderTraversal(TreeNode<T> root) {
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

        LinkedList<TreeNode<T>> stack = new LinkedList<TreeNode<T>>();
        stack.addFirst(root);
        TreeNode<T> left = root.left;
        TreeNode<T> right = root.right;

        TreeNode<T> current;
        LinkedList<TreeNode<T>> path = new LinkedList<TreeNode<T>>();
        while (!stack.isEmpty()) {
            current = stack.pollFirst();
            if (current != null) {
                path.addFirst(current);
                stack.addFirst(current.right);
                stack.addFirst(current.left);
            }
        }

        for (TreeNode<T> b : path) {
            System.out.print(b.value);
        }
        System.out.println("");
    }

    public void midOderListNoIter() {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode<T>> stack = new LinkedList<TreeNode<T>>();
        TreeNode<T> current = root;
        TreeNode<T> tmp;
        LinkedList<TreeNode<T>> path = new LinkedList<TreeNode<T>>();
        while (current!=null || !stack.isEmpty()) {
            while (current != null) {
                stack.addFirst(current);
                current = current.left;
            }

            tmp = stack.pollFirst();
            path.add(tmp);
            current = tmp.right;
        }

        for (TreeNode<T> b : path) {
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

        LinkedList<TreeNode<T>> stack = new LinkedList<TreeNode<T>>();
        stack.addFirst(root);
        TreeNode<T> left = root.left;
        TreeNode<T> right = root.right;

        TreeNode<T> current;
        LinkedList<TreeNode<T>> path = new LinkedList<TreeNode<T>>();
        while (!stack.isEmpty()) {
            current = stack.pollFirst();
            if (current != null) {
                path.addFirst(current);
                stack.addFirst(current.left);
                stack.addFirst(current.right);
            }
        }

        for (TreeNode<T> b : path) {
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
    public void insert(TreeNode<T> parent, T value, boolean leftChild) {

    }

    /**
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
     * @param n
     * @return
     */
    public List<TreeNode<Integer>> generateTrees(int n ){
        if(n<=0){
            return new ArrayList<TreeNode<Integer>>();
        }

        return generateTrees(1, n);

    }

    LinkedList<TreeNode<Integer>> generateTrees(int start, int end) {
        LinkedList<TreeNode<Integer>> allTrees = new LinkedList<>();
        if(start>end){
            allTrees.add(null);
            return allTrees;
        }

        for(int i=start;i<=end;++i) {
            LinkedList<TreeNode<Integer>> leftTrees = generateTrees(start, i - 1);
            LinkedList<TreeNode<Integer>> rightTrees = generateTrees(i + 1, end);

            for(TreeNode<Integer> left:leftTrees) {
                for (TreeNode<Integer> right:rightTrees) {
                    TreeNode<Integer> currentRoot = new TreeNode<Integer>(i);
                    currentRoot.left = left;
                    currentRoot.right = right;
                    allTrees.add(currentRoot);
                }
            }
        }
        return allTrees;
    }




    public static class TreeNode<T> {
        T value;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }
}


