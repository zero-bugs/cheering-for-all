package com.zero.bugs.demo;

import com.zero.bugs.demo.algorithm.repository.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {

    }


    /**
     * 中序遍历
     * @param root
     * @return
     */
    public boolean isValidBSTPreOrder(TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        List<TreeNode> path = new LinkedList<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.addFirst(current);
                current = current.left;
            }


            TreeNode tmp = stack.pollFirst();
            path.add(tmp);
            current = tmp != null ? tmp.right : null;
        }
        for(int i=1;i<path.size();++i){
            if (path.get(i-1).val>=path.get(i).val){
                return false;
            }
        }
        return true;
    }


    /**
     * 递归法
     * @param root
     * @return
     */
    public boolean isValidBSTIter(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isValidBSTIter(root, null, null);
    }

    private boolean isValidBSTIter(TreeNode root, Integer lower, Integer higher) {
        if (root == null) return true;
        if (lower!=null && root.val <= lower) return false;
        if (higher!=null && root.val >= higher) return false;

        if (!isValidBSTIter(root.left, lower, root.val)) return false;
        if (!isValidBSTIter(root.right, root.val, higher)) return false;
        return true;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}