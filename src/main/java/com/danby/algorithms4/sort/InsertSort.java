package com.danby.algorithms4.sort;

import static com.danby.algorithms4.utils.SortUtil.*;

public class InsertSort {
    public static void sort(Comparable[] a) {
        // 遍历数组
        for (int i = 1; i < a.length; i++) {
            // 将当前位置的元素插入到前面的有序数组中
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {3, 2, 1, 5, 4};
        sort(a);
        show(a);
    }
}
