package com.zero.bugs.demo.algorithm.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TreeRelatedAlgorithm {
    public static final int INVALID = -0xffff;
    Map<Integer, Integer> mapPre = new HashMap<>();

    Map<Integer, Integer> mapIn = new HashMap<>();

    Map<Integer, Integer> mapPost = new HashMap<>();

    TreeNode rootT;

    public void constructTree(int[] data) {
        if (data == null || data.length == 0) {
            return;
        }

        if (data.length == 1 && data[0] != INVALID) {
            rootT = new TreeNode(data[0]);
            return;
        }

        if (data[0] == INVALID) {
            return;
        }

        rootT = new TreeNode(data[0]);
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(rootT);

        int maxParentIndex = data.length / 2 - 1;
        for (int i = 0; i <= maxParentIndex; ++i) {
            TreeNode left = null;
            TreeNode right = null;
            if (data[2 * i + 1] != INVALID) {
                left = new TreeNode(data[2 * i + 1]);
            }
            if (2 * i + 2 < data.length && data[2 * i + 2] != INVALID) {
                right = new TreeNode(data[2 * i + 2]);
            }

            TreeNode current = queue.pollLast();
            current.left = left;
            current.right = right;

            if (left != null) {
                queue.addFirst(left);
            }
            if (right != null) {
                queue.addFirst(right);
            }
        }
    }


    public void constructTreeRecur(int[] datas) {
        rootT = constructTreeRecur(datas, 0);
    }

    private TreeNode constructTreeRecur(int[] datas, int i) {
        if (i >= datas.length) {
            return null;
        }
        if (datas[i] == INVALID) {
            return null;
        }

        TreeNode root = new TreeNode(datas[i]);
        root.left = constructTreeRecur(datas, 2 * i + 1);
        root.right = constructTreeRecur(datas, 2 * i + 2);
        return root;
    }

    /**
     * 广度优先遍历 核心函数
     * @param root
     * @return
     */
    public List<List<TreeNode>> BFS(TreeNode root) {
        List<List<TreeNode>> result = new LinkedList<>();
        if (root == null) return result;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        while (!queue.isEmpty()){
            List<TreeNode> lst = new LinkedList<>();
            int length = queue.size();
            for (int i=0;i<length;++i) {
                TreeNode node = queue.pollLast();
                lst.add(node);

                if (node.left!=null) queue.addFirst(node.left);
                if (node.right !=null) queue.addFirst(node.right);
            }
            result.add(lst);
        }

        for(List<TreeNode> lst : result) {
            System.out.print("[");
            for(TreeNode l:lst){
                System.out.print(l.val + ",");
            }
            System.out.println("]");
        }
        return result;
    }


    /**
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new LinkedList<>();
        }

        return generateTrees(1, n);
    }

    private LinkedList<TreeNode> generateTrees(int start, int end) {
        LinkedList<TreeNode> trees = new LinkedList<>();
        //没有树也要放入null结果，以供后续遍历使用
        if (start > end) {
            trees.add(null);
            return trees;
        }

        for (int i = start; i <= end; ++i) {
            //左侧可能所有子树
            LinkedList<TreeNode> leftTrees = generateTrees(start, i - 1);
            //右侧所有可能子树
            LinkedList<TreeNode> rightTrees = generateTrees(i + 1, end);
            for (TreeNode l : leftTrees) {
                for (TreeNode r : rightTrees) {
                    TreeNode current = new TreeNode(i);
                    current.left = l;
                    current.right = r;
                    trees.add(current);
                }
            }
        }
        return trees;
    }

    /**
     * 卡特兰树
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 0;
        for (int i = 1; i <= n; ++i) {
            G[i] = G[i - 1] * 2 * (2 * i + 1) / (i + 2);
        }
        return G[n];
    }

    /**
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        boolean leftRight = true;
        while (!queue.isEmpty()) {
            int length = queue.size();
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < length; ++i) {
                TreeNode node = queue.pollLast();
                list.add(node.val);
                if (node.left != null) {
                    queue.addFirst(node.left);
                }
                if (node.right != null) {
                    queue.addFirst(node.right);
                }
            }
            result.add(list);
        }

        for (int i = 0; i < result.size(); ++i) {
            if (i % 2 == 0) {
                Collections.reverse(result.get(i));
            }
        }

        return result;
    }

    /**
     * 先序和中序构造数
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreePreInTraversal(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length < 1 || inorder.length < 1
            || preorder.length != inorder.length) {
            return null;
        }

        for (int i = 0; i < preorder.length; ++i) {
            mapPre.put(preorder[i], i);
            mapIn.put(inorder[i], i);
        }
        return generateByPreInTraversal(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode generateByPreInTraversal(int[] preorder, int p1, int p2, int[] inorder, int i1, int i2) {
        if (p1 > p2 || i1 > i2) {
            return null;
        }

        //前序遍历第一个节点是根节点；
        int inRootIndex = mapIn.get(preorder[p1]);

        TreeNode root = new TreeNode(preorder[p1]);
        root.left = generateByPreInTraversal(preorder, p1 + 1, p1 + inRootIndex - i1, inorder, i1, inRootIndex - 1);
        root.right = generateByPreInTraversal(preorder, p1 + inRootIndex - i1 + 1, p2, inorder, inRootIndex + 1, i2);
        return root;
    }

    /**
     * 中序后续构建数
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length < 1 || postorder.length < 1
            || inorder.length != postorder.length) {
            return null;
        }

        for (int i = 0; i < inorder.length; ++i) {
            mapIn.put(inorder[i], i);
        }

        return generateByInPostTraversal(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);

    }

    private TreeNode generateByInPostTraversal(int[] inorder, int[] postorder, int i1, int i2, int p1, int p2) {
        if (p1 > p2 || i1 > i2) {
            return null;
        }

        int postRootIndex = p2;
        int inRootIndex = mapIn.get(postorder[p2]);

        TreeNode root = new TreeNode(postorder[p2]);
        root.left = generateByInPostTraversal(inorder, postorder, i1, inRootIndex - 1, p1, p1 + inRootIndex - i1 - 1);
        root.right = generateByInPostTraversal(inorder, postorder, inRootIndex + 1, i2, p1 + inRootIndex - i1,
            postRootIndex - 1);
        return root;
    }

    /**
     *  将有序数组转换为二叉搜索树左右高度差为1
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, end);
        return root;
    }

    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root){
        if (root == null) {
            return true;
        }
        if (Math.abs(treeHigh(root.left) - treeHigh(root.right)) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int treeHigh(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return 1 + treeHigh(root.right);
        }
        if (root.right == null) {
            return 1 + treeHigh(root.left);
        }

        return 1 + Math.max(treeHigh(root.left), treeHigh(root.right));
    }

    /**
     * 给定一个二叉树，找出其最小深度。
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return 1 + minDepth(root.right);
        }
        if (root.right == null) {
            return 1 + minDepth(root.left);
        }

        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    /**
     * 给定一个二叉树，找出其最大深度。
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return 1 + maxDepth(root.right);
        }
        if (root.right == null) {
            return 1 + maxDepth(root.left);
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * 给定一个N叉树，找出其最大深度。
     * @param root
     * @return
     */
    public int maxDepth(NodeN root) {
        if (root == null) {
            return 0;
        }
        if (root.children.isEmpty()) {
            return 1;
        }

        List<Integer> result = new ArrayList<>();
        for (NodeN n : root.children) {
            result.add(1 + maxDepth(n));
        }
        return Collections.max(result);
    }

    /**
     * 根据排序数据产生二叉搜索树
     * @param datas
     * @param start
     * @param end
     * @return
     */
    public List<TreeNode> generateTree(int[] datas, int start, int end) {
        List<TreeNode> result = new LinkedList<>();
        if (start > end) {
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; ++i) {
            List<TreeNode> left = generateTree(datas, start, i - 1);
            List<TreeNode> right = generateTree(datas, i + 1, end);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode node = new TreeNode(datas[i]);
                    node.left = l;
                    node.right = r;
                    result.add(node);
                }
            }
        }

        return result;
    }

    public void printMsg(List<TreeNode> result) {
        for (TreeNode node : result) {
            BFS(node);
            System.out.println("--------");
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        LinkedList<List<TreeNode>> paths = new LinkedList<>();
        LinkedList<TreeNode> path = new LinkedList<>();
        printAllPath(root, path, paths);
        for (List<TreeNode> p : paths) {
            int cSum = 0;
            for (TreeNode t : p) {
                cSum += t.val;
//                System.out.print(t.val + ",");
            }
//            System.out.println("-----------------sum:" + cSum);
            if (cSum == sum) {
                return true;
            }
        }
        return false;
    }

    /**
     * 二叉树所有路径，核心函数
     * @param root
     * @param path
     * @param paths
     */
    public void printAllPath(TreeNode root, LinkedList<TreeNode> path, LinkedList<List<TreeNode>> paths) {
        if (root == null) {
            return;
        }

        path.addFirst(new TreeNode(root.val));
        if (root.left == null && root.right == null) {
            LinkedList<TreeNode> ll = new LinkedList<>(path);
            Collections.reverse(ll);
            paths.add(ll);
        } else {
            printAllPath(root.left, path, paths);
            printAllPath(root.right, path, paths);
        }
        path.pollFirst();
    }

    /**
     * 数组展开为单链表
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) return;

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addFirst(root);
        TreeNode newRoot  = new TreeNode(-1);
        TreeNode preNode = newRoot;
        while (!stack.isEmpty()) {
            TreeNode current = stack.pollFirst();
            preNode.right = current;
            preNode.left = null;

            if (current.right!= null) stack.addFirst(current.right);
            if (current.left!= null) stack.addFirst(current.left);
            preNode = current;
        }
    }









    public TreeNode getRootT() {
        return rootT;
    }
}

class TreeNode {
    int val;

    TreeNode left;

    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class NodeN {
    public int val;
    public List<NodeN> children;

    public NodeN() {}

    public NodeN(int _val,List<NodeN> _children) {
        val = _val;
        children = _children;
    }
}
