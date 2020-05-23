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


    public void recoverTree(TreeNode root) {
        TreeNode first = null;
        TreeNode second = null;

        if (root == null) {
            return;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        TreeNode parent = new TreeNode(Integer.MIN_VALUE);
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.addFirst(current);
                current = current.left;
            }

            current = stack.pollFirst();
            if (first == null && parent.val > current.val) first = parent;
            if (first != null && parent.val > current.val) second = current;
            parent = current;
            current = current.right;
        }

        if (first == null || second == null) return;
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        LinkedList<TreeNode> stack1 = new LinkedList<>();
        LinkedList<TreeNode> stack2 = new LinkedList<>();
        stack1.addFirst(root);
        stack2.addFirst(root);

        TreeNode node1;
        TreeNode node2;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            node1 = stack1.pollFirst();
            node2 = stack2.pollFirst();
            if (node1 == null && node2 == null) continue;
            if (node1 == null || node2 == null) return false;
            if (node1.val != node2.val) return false;

            stack1.addFirst(node1.right);
            stack1.addFirst(node1.left);

            stack2.addFirst(node2.left);
            stack2.addFirst(node2.right);
        }

        return stack1.isEmpty() && stack2.isEmpty();
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return new LinkedList<>();

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>(0);
            int length = queue.size();
            while (length > 0) {
                TreeNode node = queue.pollLast();
                list.add(node.val);
                if (node.left != null) {
                    queue.addFirst(node.left);
                }
                if (node.right != null) {
                    queue.addFirst(node.right);
                }
                --length;
            }
            result.add(list);
        }
        return result;
    }



    public List<List<Integer>> levelOrderDownUp(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null) return new LinkedList<>();

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            int length = queue.size();
            while (length > 0) {
                TreeNode node = queue.pollLast();
                list.add(node.val);
                if (node.left != null) {
                    queue.addFirst(node.left);
                }
                if (node.right != null) {
                    queue.addFirst(node.right);
                }
                --length;
            }
            result.addFirst(list);
        }
        return result;
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}