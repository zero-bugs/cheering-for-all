package com.zero.bugs.demo.algorithm.repository;

import java.util.Arrays;

/**
 * 树的数组存储方式
 * @param T>
 */
public class ArrayBinTree<T> {
    private final int DEFAULT_DEEP=8;
    private Object[] datas;
    private int deep;
    private int arraySize;

    public ArrayBinTree() {
        this.deep = DEFAULT_DEEP;
        this.arraySize = (int) Math.pow(2, deep);
        datas = new Object[arraySize];
    }

    public ArrayBinTree(int deep) {
        this.deep = deep;
        this.arraySize = (int) Math.pow(2, deep);
        datas = new Object[arraySize];
    }

    public ArrayBinTree(int deep, T data) {
        this.deep = deep;
        this.arraySize = (int) Math.pow(2, deep);
        datas = new Object[arraySize];
        datas[0] = data;
    }

    /**
     * 为指定节点添加子节点
     *
     * @param index 需要添加子节点的父节点索引
     * @param data 新的子节点的数据
     * @param left 是否为左节点
     */
    public void add(int index, T data, boolean left) {
        if (index >= this.arraySize / 2 - 1) {
            printMsg("array out of bound for max array size is " + this.arraySize);
            return;
        }

        if (datas[index] == null) {
            printMsg("parent node is null, cannot add child node.");
            return;
        }

        if (left) {
            if (datas[2 * index + 1] == null) {
                datas[2 * index + 1] = data;
            } else {
                printMsg("node has existed, add left failed.");
                return;
            }
            return;
        }

        if (datas[2 * index + 2] == null) {
            datas[2 * index + 2] = data;
        } else {
            printMsg("node has existed, add right node failed.");
            return;
        }
    }

    /**
     * 判断树是否为空
     * @return
     */
    public boolean isEmpty() {
        return datas[0] == null;
    }

    /**
     * 获取跟节点的值
     * @return
     */
    public T getRoot() {
        return (T) datas[0];
    }

    /**
     * 获取父节点
     * @param index
     * @return
     */
    public T getParent(int index) {
        if (index == 0) {
            printMsg("root node has not parent.");
            return null;
        }

        return (T) datas[(index - 1) / 2];
    }

    /**
     * 获取左子节点
     * @param index
     * @return
     */
    public T getLeft(int index) {
        if (2*index + 1 > arraySize || 2* index + 2 > arraySize) {
            printMsg("right child node not exist");
            return null;
        }

        return (T) datas[index * 2 +1];
    }


    public int getDeep(){
        return deep;
    }

    public int getPos(T data) {
        for(int i=0;i<arraySize; ++i) {
            if (datas[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        return Arrays.toString(datas);
    }
    private void printMsg(String msg) {
        System.out.println(msg);
    }
}
