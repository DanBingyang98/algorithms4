package com.danby.algorithms4.sort;

import static com.danby.algorithms4.utils.SortUtil.*;

public class QuickSort {

    // 对数组a进行排序
    public static void sort(Comparable[] a) {

        // 调用sort方法，对数组a进行排序
        sort(a, 0, a.length - 1);
    }

    // 对数组a的子数组a[lo...hi]进行排序
    public static void sort(Comparable[] a, int lo, int hi) {
        // 如果子数组长度小于等于1，则直接返回
        if (lo >= hi) {
            return;
        }
        // 调用partition方法，将数组a的子数组a[lo...hi]分为两部分
        int p = partition(a, lo, hi);
        // 对左半部分进行排序
        sort(a, lo, p - 1);
        // 对右半部分进行排序
        sort(a, p + 1, hi);
    }

    // 将数组a的子数组a[lo...hi]分为两部分，左边小于等于v，右边大于v
    public static int partition(Comparable[] a, int lo, int hi) {
        // 定义两个指针i和j，分别指向子数组的左边界和右边界
        int i = lo, j = hi + 1;
        // 定义一个基准元素v，取子数组的第一个元素
        Comparable v = a[lo];
        // 当i和j没有相遇时，继续循环
        while (true) {
            // 从左到右找到第一个大于v的元素
            while (less(a[++i], v)) if (i >= hi) break;
            // 从右到左找到第一个小于v的元素
            while (less(v, a[--j])) ;
            // 如果i和j相遇，则退出循环
            if (i >= j) break;
            // 交换a[i]和a[j]
            exch(a, i, j);
        }
        // 将基准元素v放到正确的位置
        exch(a, lo, j);
        // 返回基准元素v的位置
        return j;
    }

    public static void main(String[] args) {
        char[] array = "QUICKSORTEXAMPLE".toCharArray();
        Character[] a = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            a[i] = array[i];
        }
        sort(a);
        show(a);

    }
}
