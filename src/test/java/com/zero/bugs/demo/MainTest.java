package com.zero.bugs.demo;

import com.zero.bugs.demo.algorithm.repository.BinaryTree;

public class MainTest {
    public static void main(String[] args) {
        String[] values = {"A","B","C","D","E","F","G","H","I","J","k"};

        BinaryTree<String> binaryTree = new BinaryTree<>();
        binaryTree.custructBinaryTree(values);
        binaryTree.preOrderTraversal();
        binaryTree.midOrderTraversal();
        binaryTree.backOrderTraversal();

        binaryTree.custructBinaryTreeIter(values);
        binaryTree.preOrderTraversal();
        binaryTree.preOrderTraversalNoIter();

        binaryTree.midOrderTraversal();
        binaryTree.midOderListNoIter();

        binaryTree.backOrderTraversal();
        binaryTree.backOderTraversalNoIter();
        
        
        
        Integer[] values = new Integer[]{12,8,10,6,4,7,9};
        HeapStackService<Integer> service = new HeapStackService<>();
        service.buildMaxVertexHeap(values);
        service.preOrderTraversal();
        service.midOrderTraversalInner();
        service.backOrderTraversalInner();
        service.preOrderTraversalRight();
        service.arrayTraversal();
    }
}
