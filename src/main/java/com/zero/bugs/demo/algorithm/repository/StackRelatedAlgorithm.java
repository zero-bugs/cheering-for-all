package com.zero.bugs.demo.algorithm.repository;

import java.util.LinkedList;

public class StackRelatedAlgorithm {

    /**
     * 接雨水
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int ans = 0;
        int i;
        LinkedList<Integer> stack = new LinkedList<>();
        for (i = 0; i < height.length; ++i) {
            while (!stack.isEmpty() && height[stack.peekFirst()] < height[i]) {
                int topIndex = stack.pollFirst();
                if (stack.isEmpty()) {
                    break;
                }
                int distance = i - stack.peekFirst() - 1;
                int boundHeight = Math.min(height[i], height[stack.peekFirst()]) - height[topIndex];
                ans += distance * boundHeight;
            }
            stack.addFirst(i);
        }
        return ans;
    }

    public int trapDoublePoint(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int ans = 0;
        int leftIndex = 0;
        int rightIndex = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        while (leftIndex < rightIndex) {
            if (height[leftIndex] < height[rightIndex]) {
                if (height[leftIndex] > leftMax) {
                    leftMax = height[leftIndex];
                } else {
                    ans += (leftMax - height[leftIndex]);
                }
                ++leftIndex;
            } else {
                if (height[rightIndex] > rightMax) {
                    rightMax = height[rightIndex];
                } else {
                    ans += (rightMax - height[rightIndex]);
                }
                --rightIndex;
            }
        }
        return ans;
    }

    /**
     * Unix风格的字符串标准化
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        if (path == null || path.isEmpty()) {
            return "";
        }
        LinkedList<String> stack = new LinkedList<>();
        String[] pathArr = path.split("/");
        int i;
        for (i = 0; i < pathArr.length; ++i) {
            if (pathArr[i].equals(".")) {
                continue;
            } else if (pathArr[i].equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pollFirst();
                }
            } else {
                if (!pathArr[i].equals("")) {
                    stack.addFirst(pathArr[i]);
                }
            }
        }

        if (stack.isEmpty()) {
            return "/";
        }

        StringBuilder builder = new StringBuilder(0);
        for (i = stack.size() - 1; i >= 0; --i) {
            builder.append("/" + stack.get(i));
        }

        return builder.toString();
    }
}
