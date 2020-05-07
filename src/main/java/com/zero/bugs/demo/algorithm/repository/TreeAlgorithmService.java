package com.zero.bugs.demo.algorithm.repository;

public class TreeAlgorithmService {
    Node root;

    /**
     * 插入节点
     * @param value
     */
    public void insert(int value) {
        root = insert(root, value);
    }

    /**
     * 删除节点
     * @param value
     * @return
     */
    public void delete(int value) {
        root = delete(root, value);
    }

    /**
     * 先序遍历
     */
    public void inOrderTravel() {
        inOrderTravel(root);
    }

    /**
     * 中序遍历
     */
    public void midOrderTravel() {
        midOrderTravel(root);
    }

    /**
     * 后序遍历
     */
    public void backOrderTravel() {
        backOrderTravel(root);
    }

    private Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }
        else if (root.value < value) {
            root.right = insert(root.right, value);
        } else {
            root.left = insert(root.left, value);
        }
        return root;
    }

    private Node delete(Node root, int value) {
        if (root == null) {
            return null;
        }

        if (root.value<value){
            root.right = delete(root.right, value);
        } else if (root.value>value){
            root.left = delete(root.left, value);
        } else {
            // 情况1：叶子节点
            if (root.left != null && root.right != null){
                Node target = root.left;
                while (target.right != null) {
                    target = target.right;
                }
                root = delete(root, target.value);
                root.value = target.value;
            } else if (root.left !=null){
                root = root.left;
            } else if (root.right != null){
                root= root.right;
            } else {
                root = null;
            }
        }

        return root;
    }

    private void inOrderTravel(Node root) {
        if (root == null) {
            return;
        }

        inOrderTravel(root.left);
        System.out.print(root.value + ",");
        inOrderTravel(root.right);
    }

    public void midOrderTravel(Node root){
        if (root == null) {
            return;
        }

        System.out.print(root.value + ",");
        inOrderTravel(root.left);
        inOrderTravel(root.right);
    }

    public void backOrderTravel(Node root) {
        if (root == null) {
            return;
        }

        inOrderTravel(root.left);
        inOrderTravel(root.right);
        System.out.print(root.value + ",");
    }

    public Node getRoot() {
        return root;
    }
}

class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
}
