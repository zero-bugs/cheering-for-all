package com.zero.bugs.demo.algorithm.repository;

public class IteratorAlgorithmUtils {

    /**
     * 细菌分裂问题，母体消亡
     * @param month 第n个月
     * @param ratio 增长率
     */
    public static void bacteriaDivision(int month, int ratio) {
        long total = 1;
        int i;
        for (i = 1; i <= month; ++i) {
            total = ratio * total;
        }
        System.out.println("After month:" + month + ", total number is " + total);
    }

    /**
     * 兔子繁殖问题（斐波那契数列）
     * @param month 第n个月份
     * @param ratio 繁殖率
     */
    public static void rabbitReproduce(int month, int ratio) {
        if (month <= 0) {
            return;
        }

        if (month <= 1) {
            System.out.println(new int[] {1, 2}[month - 1]);
        }

        int f1 = 1;
        int f2 = 2;
        int f3 = 0;
        int k;
        for (k = 3; k <= month; ++k) {
            f3 = f1*ratio + f2;
            f1 = f2;
            f2 = f3;
        }
        System.out.println("rabbit count after " + month + " month at ratio " + ratio + " is " + f3);
    }

    /**
     * 打印指定最大值以内的斐波那契额数列
     * @param max
     */
    public static void printFbnc(int max) {
        int f1 = 0;
        int f2 = 1;
        int f3;
        while (true) {
            f3 = f1 + f2;
            if (f3 > max) {
                break;
            }
            System.out.print(f3 + ",");
            f1 = f2;
            f2 = f3;
        }
        System.out.println("");
    }

    /**
     * 打印第index个斐波那契数
     * @param index
     */
    public static void findFbnc(int index) {
        if (index<=0){
            return;
        }

        if (index<=2){
            System.out.println(new int[]{1,2}[index-1]);
        }

        int f1=1;
        int f2=2;
        int f3 = 0;
        int i;
        for (i=3;i<=index;++i){
            f3 = f1+f2;
            f1=f2;
            f2=f3;
        }
        System.out.println(f3);
    }

    /**
     * 斐波那契数列递归实现
     * @param index 第n个数
     * @return
     */
    public static int findFbncRecursion(int index) {
        if (index < 0) return 0;
        if (index <= 2) return new int[] {0, 1, 2}[index];
        return findFbncRecursion(index - 1) + findFbncRecursion(index - 2);
    }

    /**
     * 猴子吃桃子问题
     * @param day 第n天
     * @param left 剩余n个
     */
    public static void monkeyEatPeach(int day, int left) {
        int total = left;
        int i;
        for (i = day - 1; i > 0; --i) {
            total = 2 * (total + 1);
        }
        System.out.println("total:" + total);
    }

    /**
     * 平方根
     * @param val
     * @return
     */
    public static int mySqrt(int val) {
        if (val <= 0) return 0;
        if (val == 1) return 1;

        int start = 0;
        int end = val / 2 + 1;
        double value;
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            value = (double) mid * (double) mid;
            if (value <= val) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return start;
    }

    /**
     * 汉诺塔问题
     * @param num a柱子盘子数
     * @param a 当前递归的初始盘子所在的柱子
     * @param b 当前递归要借用的柱子
     * @param c 要移动到的柱子
     */
    public static void hanoiTower(int num, int a, int b, int c) {
        if (num == 1) {
            System.out.println((char)a + "->" + (char) c);
            return;
        }

        // a柱子上面的n-1个，通过c按照从小到达的规模先移动到缓冲区b，再递归
        hanoiTower(num - 1, a, c, b);

        // n-1个盘子移动之后，剩余一个盘子的移动
        hanoiTower(1, a, b, c);

        // n-1个缓冲区的盘子移动到c
        hanoiTower(num - 1, b, a, c);
    }
}
