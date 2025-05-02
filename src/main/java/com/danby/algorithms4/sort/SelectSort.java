package com.danby.algorithms4.sort;

import static com.danby.algorithms4.utils.SortUtil.*;

public class SelectSort {
    // 对数组a进行选择排序
    public static void sort(Comparable[] a) {
        // 遍历数组
        for (int i = 0; i < a.length; i++) {
            // 将当前位置设为最小值
            int min = i;
            // 从当前位置的下一个位置开始遍历
            for (int j = i + 1; j < a.length; j++) {
                // 如果找到比当前位置更小的值，则更新最小值
                if (less(a[j], a[min]))
                    min = j;
            }
            // 将最小值与当前位置交换
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        // 创建一个整型数组
        Integer[] a = { 5, 4, 3, 2, 1 };
        // 对数组进行排序
        sort(a);
        // 显示排序后的数组
        show(a);
    }
}
