package com.danby.algorithms4.sort.exercise;

import static com.danby.algorithms4.utils.SortUtil.*;

/**
 * 2.1.25 不需要交换的插入排序。
 * 再插入排序的实现中使较大元素右移一位只需要访问一次数组（而不使用exch()）。
 * 使用SortCompare来评估这种做法的效果。
 */

public class Exercise2125 {
    // 对数组a进行排序
    public static void sort(Comparable[] a) {
        // 从第二个元素开始遍历数组
        for (int i = 1; i < a.length; i++) {
            // 保存当前元素
            Comparable temp = a[i];
            // 从当前元素的前一个元素开始向前遍历
            int j;
            for (j = i - 1; j >= 0 && less(temp, a[j]); j--) {
                // 将当前元素的前一个元素向后移动一位
                a[j + 1] = a[j];
            }
            // 将当前元素插入到正确的位置
            a[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        Integer[] a = {3, 2, 1, 5, 4};
        sort(a);
        show(a);
    }
}
