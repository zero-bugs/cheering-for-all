package com.zero.bugs.demo;

import com.zero.bugs.demo.algorithm.repository.ArrayBinTree;

public class MainTest {
    public static void main(String[] args) {

        ArrayBinTree<String> binTree = new ArrayBinTree<>(4, "根");

        binTree.add(0, "0右", false);
        binTree.add(2, "2右", false);
        binTree.add(2, "2左", true);
        binTree.add(0, "0左", true);
        binTree.add(1, "1左", true);

        System.out.println(binTree);
        System.out.println(binTree.getLeft(2));
        System.out.println(binTree.getParent(6));
    }
}
