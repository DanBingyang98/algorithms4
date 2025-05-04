package com.danby.algorithms4.sort;

import java.util.Arrays;

import static com.danby.algorithms4.utils.SortUtil.less;
import static com.danby.algorithms4.utils.SortUtil.show;

public class MergeSort {
    public static Comparable[] aux;

    // 原地归并的抽象方法
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        // 定义两个指针i和j，分别指向左半部分和右半部分的起始位置
        int i = lo, j = mid + 1, k;
        // 将数组a中的元素复制到辅助数组aux中
        for (k = lo; k <= hi; k++)
            aux[k] = a[k];
        // 从左到右依次比较左半部分和右半部分的元素，将较小的元素放入数组a中
        for (k = lo; k <= hi; k++)
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public static void sort2(Comparable[] a) {
        int N = a.length;
        // 创建一个与a相同长度的辅助数组
        aux = new Comparable[N];
        // 从1开始，每次增加2的倍数
        for (int sz = 1; sz < N; sz = sz + sz) {
            // 从0开始，每次增加2的倍数
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                // 合并两个有序数组
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        // 如果hi小于等于lo，说明数组已经排序完成，直接返回
        if (hi <= lo) return;
        // 计算中间位置
        int mid = lo + (hi - lo) / 2;
        // 递归排序左半部分数组
        sort(a, lo, mid);
        // 递归排序右半部分数组
        sort(a, mid + 1, hi);
        // 合并左右两部分数组
        merge(a, lo, mid, hi);
    }

    public static void main(String[] args) {
        char[] array = "MERGESORTEXAMPLE".toCharArray();
        Character[] a = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            a[i] = array[i];
        }
        sort2(a);
        show(a); //A E E I N O Q S S T U Y
    }
}
