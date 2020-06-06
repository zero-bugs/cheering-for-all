package com.zero.bugs.demo.algorithm.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class StringRelatedAlgorithm {

    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * 无重复字符的最长子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }

        int ans = 0;
        int start = 0;
        int end;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (end = 0; end < s.length(); ++end) {
            Character ch = s.charAt(end);
            if (!map.isEmpty() && map.containsKey(ch)) {
                start = Math.max(start, map.get(ch));
            }
            ans = Math.max(ans, end - start + 1);
            map.put(ch, end + 1);
        }

        return ans;
    }

    /**
     * 最长回文
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    /**
     * 整形转罗马
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        // Loop through each symbol, stopping if num becomes 0.
        for (int i = 0; i < values.length && num >= 0; i++) {
            // Repeat while the current symbol still fits into num.
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }

    public int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int ans = 0;
        for (int i = 0; i < s.length(); ) {
            if (i + 1 < s.length() && map.containsKey(s.substring(i, i + 2))) {
                ans += map.get(s.substring(i, i + 2));
                i += 2;
            } else {
                ans += map.get(s.substring(i, i + 1));
                i++;
            }
        }
        return ans;
    }

    /**
     * 14. 最长公共前缀
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        if (strs.length == 1) {
            return strs[0];
        }

        int i;
        int start = 0;
        boolean isOutOfBound;
        StringBuilder ans = new StringBuilder();
        Set<Character> set = new HashSet<>();
        while (true) {
            isOutOfBound = false;
            for (i = 0; i < strs.length; ++i) {
                if (start >= strs[i].length()) {
                    isOutOfBound = true;
                    break;
                }
            }

            if (isOutOfBound) {
                break;
            }

            set.clear();
            for (i = 0; i < strs.length; ++i) {
                set.add(strs[i].charAt(start));
            }

            if (set.size() != 1) {
                break;
            } else {
                ans.append(strs[0].charAt(start));
                ++start;
            }
        }
        return ans.toString();
    }

    private String letterMap[] = {
        " ",    //0
        "",     //1
        "abc",  //2
        "def",  //3
        "ghi",  //4
        "jkl",  //5
        "mno",  //6
        "pqrs", //7
        "tuv",  //8
        "wxyz"  //9
    };

    private ArrayList<String> res = new ArrayList<String>();

    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return res;
        }

        findCombination(digits, 0, "");
        return res;
    }

    private void findCombination(String digits, int index, String s) {

        if (index == digits.length()) {
            res.add(s);
            return;
        }

        Character c = digits.charAt(index);
        String letters = letterMap[c - '0'];
        for (int i = 0; i < letters.length(); i++) {
            findCombination(digits, index + 1, s + letters.charAt(i));
        }
    }

    /**
     * 括号生成
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        dfs(n, n, "");
        return res;
    }

    private void dfs(int left, int right, String s) {
        if (left == 0 && right == 0) {
            res.add(s);
            return;
        }

        if (left > 0) {
            dfs(left - 1, right, s + "(");
        }

        if (left < right) {
            dfs(left, right - 1, s + ")");
        }
    }

    /**
     * 实现strstr（）函数
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) {
            return -1;
        }
        int hLen = haystack.length();
        int nLen = needle.length();
        int i, j;
        for (i = 0; i <= hLen - nLen; ++i) {
            for (j = 0; j < nLen; ++j) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == nLen) {
                return i;
            }
        }
        return -1;
    }

    public List<List<Integer>> result = new LinkedList<>();

    /**
     * 排列。有重复数字全排列，需要提前排序；
     * 兼容无重复数字全排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) {
            return result;
        }
        //首先给数组排序
        Arrays.sort(nums);
        findUnique(nums, new boolean[nums.length], new LinkedList<Integer>());
        return result;
    }

    public void findUnique(int[] nums, boolean[] visited, LinkedList<Integer> trace) {
        //结束条件
        if (trace.size() == nums.length) {
            result.add(new LinkedList(trace));
            return;
        }
        //选择列表
        for (int i = 0; i < nums.length; i++) {
            //其次，我们已经选择过的不需要再放进去了
            if (visited[i]) {
                continue;
            }
            //接下来，如果当前节点与他的前一个节点一样，并其他的前一个节点已经被遍历过了，那我们也就不需要了。
            if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1]) {
                break;
            }
            //做出选择
            trace.add(nums[i]);
            visited[i] = true;
            findUnique(nums, visited, trace);
            //撤销选择
            trace.removeLast();
            visited[i] = false;
        }
    }


    public static List<List<Integer>> resCom = new LinkedList<>();

    /**
     * 无重复数字组合
     *
     * 组合
     * @param nums
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0 || k > nums.length) {
            return resCom;
        }

        Arrays.sort(nums);
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, k, 0, track);
        return resCom;
    }

    private void backtrack(int[] nums, int k, int begin, LinkedList<Integer> track) {
        if (k == track.size()) {
            resCom.add(new LinkedList<>(track));
            return;
        }

        for (int i = begin; i < nums.length - (k - track.size()) + 1; ++i) {
            track.addLast(nums[i]);
            backtrack(nums, k, i + 1, track);
            track.pollLast();
        }
    }



    List<List<Integer>> ans = new ArrayList<>();

    /**
     * 有重复数字组合
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return ans;
        }
        Arrays.sort(candidates);
        helper(candidates, target, 0, 0, new ArrayList<>());
        return ans;
    }

    public void helper(int[] candidates, int target, int sum, int index, List<Integer> temp) {
        if (sum == target) {
            ans.add(new ArrayList<>(temp));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue; //continue的原因在于当前数和上一个数字大小相同，那么上个数字出现的符合预期的所有结果会包含这个数字出现的结果里。
            }
            if (sum + candidates[i] <= target) {
                temp.add(candidates[i]);
                helper(candidates, target, sum + candidates[i], i + 1, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    /**
     * 最长公共子序列
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 找到所有子序列
     * @param i
     * @param j
     * @param lcs_str
     * @param lcs_len
     */
    // void traceBack(int i, int j, string lcs_str, int lcs_len)
    // {
    //     while (i>0 && j>0)
    //     {
    //         if (X[i-1] == Y[j-1])
    //         {
    //             lcs_str.push_back(X[i-1]);
    //             --i;
    //             --j;
    //         }
    //         else
    //         {
    //             if (table[i-1][j] > table[i][j-1])
    //                 --i;
    //             else if (table[i-1][j] < table[i][j-1])
    //                 --j;
    //             else   // 相等的情况
    //             {
    //                 traceBack(i-1, j, lcs_str, lcs_len);
    //                 traceBack(i, j-1, lcs_str, lcs_len);
    //                 return;
    //             }
    //         }
    //     }
    //
    //     string str(lcs_str.rbegin(), lcs_str.rend()); // lcs_str逆序
    //     if((int)str.size() == lcs_len)                // 判断str的长度是否等于lcs_len
    //         setOfLCS.insert(str);
    // }

    /**
     * 最长公共子串，不一定在最后一个格子
     * @param s1
     * @param s2
     * @return
     */
    public static int longestCommonSubstring(String s1, String s2) {
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();
        // a.length行，b.length列
        int[][] result = new int[a.length + 1][b.length + 1];
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    result[i + 1][j + 1] = result[i][j] + 1;
                    max = Math.max(max, result[i + 1][j + 1]);
                }
            }
        }
        // ----- print table -----
        System.out.print(" ");
        for (int i = 0; i < b.length; i++) {
            System.out.print(" " + b[i]); // 打印第一行
        }

        System.out.println();
        for (int i = 1; i < result.length; i++) {
            System.out.print(a[i - 1] + " ");
            for (int j = 1; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        return max;
    }
}
