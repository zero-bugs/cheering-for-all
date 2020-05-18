package com.zero.bugs.demo.algorithm.repository;

public class HeapStackService<T extends Comparable<T>> {
    //树型存储
    private Node<T> root;

    //数组存储
    private T[] tree;

    public void buildMaxVertexHeap(T[] values) {
        if (values == null) {
            return;
        }

        //父节点索引
        int i = values.length / 2 - 1;
        for (; i >= 0; --i) {
            heapAdjust(values, i, values.length - 1);
        }

        // 转化为数组存储
        tree = values;

        // 转化为树存储
        root = create(0, tree.length);
    }

    /**
     * 数据转化为树
     * @param i
     * @param length
     */
    private Node<T> create(int i, int length) {
        Node<T> node = new Node<>(tree[i]);
        if (2 * i + 1 < length) {
            node.left = create(2 * i + 1, length);
        }

        if (2 * i + 2 < length) {
            node.right = create(2 * i + 2, length);
        }
        return node;
    }

    /**
     * 调整堆
     * @param values 堆数组
     * @param parentIndex 父节点索引
     * @param rightBorder 右边界
     */
    private void heapAdjust(T[] values, int parentIndex, int rightBorder) {
        int leftChildIndex = 2 * parentIndex + 1;
        int rightChildIndex = 2 * parentIndex + 2;

        int targetValueIndex = parentIndex;
        if (rightChildIndex <= rightBorder && values[targetValueIndex].compareTo(values[rightChildIndex]) < 0) {
            targetValueIndex = rightChildIndex;
        }

        if (leftChildIndex <= rightBorder && values[targetValueIndex].compareTo(values[leftChildIndex]) < 0) {
            targetValueIndex = leftChildIndex;
        }

        if (targetValueIndex != parentIndex) {
            swap(values, targetValueIndex, parentIndex);
            heapAdjust(values, targetValueIndex, rightBorder);
        }
    }

    private void swap(T[] values, int largestValueIndex, int parentIndex) {
        T tmp;
        tmp = values[largestValueIndex];
        values[largestValueIndex] = values[parentIndex];
        values[parentIndex] = tmp;
    }

    /**
     * 前序遍历
     */
    public void preOrderTraversal() {
        preOrderTraversalInner(root);
        System.out.println("");
    }

    private void preOrderTraversalInner(Node<T> root) {
        if(root == null) {
           return;
        }

        System.out.print(root.value+"->");
        preOrderTraversalInner(root.left);
        preOrderTraversalInner(root.right);
    }


    /**
     * 前序遍历
     */
    public void preOrderTraversalRight() {
        preOrderTraversalRightInner(root);
        System.out.println("");
    }

    private void preOrderTraversalRightInner(Node<T> root) {
        if(root == null) {
            return;
        }

        System.out.print(root.value+"->");
        preOrderTraversalRightInner(root.right);
        preOrderTraversalRightInner(root.left);
    }

    /**
     * 中序遍历
     */
    public void midOrderTraversalInner() {
        midOrderTraversalInner(root);
        System.out.println("");
    }

    private void midOrderTraversalInner(Node<T> root) {
        if(root == null) {
            return;
        }

        midOrderTraversalInner(root.left);
        System.out.print(root.value+"->");
        midOrderTraversalInner(root.right);
    }

    public void backOrderTraversalInner() {
        backOrderTraversalInner(root);
        System.out.println("");
    }

    private void backOrderTraversalInner(Node<T> root) {
        if(root == null) {
            return;
        }

        backOrderTraversalInner(root.left);
        backOrderTraversalInner(root.right);
        System.out.print(root.value+"->");
    }

    public void arrayTraversal() {
        if (tree !=null) {
            for(int i=0;i<tree.length;++i){
                System.out.print(tree[i]+"->");
            }
            System.out.println("");
        }
    }

    static class Node<T extends Comparable<T>>{
        T value;
        Node<T> left;
        Node<T> right;

         public Node(T value) {
            this.value = value;
        }
    }
}
