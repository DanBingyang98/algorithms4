package com.danby.algorithms4.utils;

public class SortUtil {
    // 比较两个元素的大小
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    // 交换数组中两个元素的位置
    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // 打印数组中的元素
    public static void show(Comparable[] a) {
        for (Comparable c : a)
            System.out.print(c + " ");
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        // 遍历数组，从第二个元素开始
        for (int i = 1; i < a.length; i++) {
            // 如果当前元素小于前一个元素，则说明数组不是有序的
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }
}
