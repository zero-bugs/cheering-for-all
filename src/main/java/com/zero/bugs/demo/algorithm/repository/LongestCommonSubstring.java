package com.zero.bugs.demo.algorithm.repository;

import java.util.HashSet;
import java.util.Set;

public class LongestCommonSubstring {
    int[][] result;

    Set<String> allResSets = new HashSet<>();

    /**
     * 最长公共子串，不一定在最后一个格子
     *
     * @param s1
     * @param s2
     * @return
     */
    public int longestCommonSubstring(String s1, String s2) {
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();
        // a.length行，b.length列
        result = new int[a.length + 1][b.length + 1];
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

    /**
     * 求出所有的最长公共子串，并放入set中
     */
    void traceBack(String s1, String s2, int lcs_len) {
        int m = s1.length();
        int n = s2.length();

        StringBuilder strOflcs = new StringBuilder(0);
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // 查到等于lcs_len的值，取字符
                if (result[i][j] == lcs_len) {
                    int ii = i, jj = j;
                    while (result[ii][jj] >= 1) {
                        strOflcs.append(s1.charAt(ii - 1));
                        ii--;
                        jj--;
                    }
                    String str = new StringBuilder(strOflcs).reverse().toString();
                    if ((int) str.length() == lcs_len)                       // 判断str的长度是否等于lcs_len
                    {
                        allResSets.add(str);
                        strOflcs = new StringBuilder(0);                           // 清空strOflcs
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String x = "ABCBDAB";
        String y = "BDCABA";

        LongestCommonSubstring ser = new LongestCommonSubstring();
        int res = ser.longestCommonSubstring(x, y);
        System.out.println(res);

        ser.traceBack(x, y, res);
        System.out.println(ser.allResSets);
    }
}
