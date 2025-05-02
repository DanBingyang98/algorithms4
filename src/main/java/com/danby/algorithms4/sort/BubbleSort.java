package com.danby.algorithms4.sort;

import static com.danby.algorithms4.utils.SortUtil.*;

public class BubbleSort {
    // 冒泡排序
    public static void sort(Comparable[] a) {
        // 标记是否发生了交换
        boolean exchanged = false;
        // 外层循环控制排序的轮数
        for (int i = 0; i < a.length - 1; i++) {
            // 内层循环控制每一轮的比较次数
            for (int j = 0; j < a.length - i - 1; j++) {
                // 如果后一个元素小于前一个元素，则交换它们的位置
                if (less(a[j + 1], a[j])) {
                    exch(a, j, j + 1);
                    exchanged = true;
                }
            }
            // 如果没有发生交换，说明数组已经有序，可以提前结束排序
            if (!exchanged) {
                break;
            } else {
                // 否则，将标记重置为false，继续下一轮排序
                exchanged = false;
            }
        }
    }

    public static void main(String[] args) {
        // 定义一个整型数组
        Integer[] a = {3, 2, 1, 5, 4};
        // 对数组进行排序
        sort(a);
        // 打印排序后的数组
        show(a);
    }
}
