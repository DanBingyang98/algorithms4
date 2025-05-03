package com.danby.algorithms4.sort;

import static com.danby.algorithms4.utils.SortUtil.less;
import static com.danby.algorithms4.utils.SortUtil.show;

public class HillSort {
    // 对数组a进行希尔排序
    public static void sort(Comparable[] a) {
        int N = a.length;
        // 初始化间隔h
        int h = 1;
        // 计算间隔h，使得h小于N/3
        while (h < N / 3) h = 3 * h + 1;
        // 当间隔h大于等于1时，进行希尔排序
        while (h >= 1) {
            // 从间隔h开始，依次比较间隔h的元素
            for (int i = h; i < N; i++) {
                // 保存当前元素
                Comparable temp = a[i];
                // 从当前元素的前一个间隔h开始，依次比较元素
                int j;
                for (j = i - h; j >= 0 && less(temp, a[j]); j -= h)
                    // 将当前元素前一个间隔h的元素后移
                    a[j + h] = a[j];
                // 将当前元素插入到正确的位置
                a[j + h] = temp;
            }
            // 将间隔h缩小为原来的1/3
            h /= 3;
        }
    }

    // 比较两个元素的大小
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    // 打印数组
    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        Integer[] a = {5, 4, 3, 2, 1, 2, 2, 4, 4, 35, 24, 324, 234, 32, 534, 5, 436, 3456, 45, 643, 56, 34543, 243};
        // 对数组a进行希尔排序
        sort(a);
        // 打印排序后的数组
        show(a);
    }
}
