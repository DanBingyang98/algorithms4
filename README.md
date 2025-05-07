

# 《算法第四版》 学习笔记



## 排序

**工具类：**

```java
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
```



### 选择排序

**思想：**

1. 找到数组中最小的元素；
2. 将找到的最小元素和数组的第一个元素交换位置；
3. 从剩下的元素中再找到一个最小的元素，并将它和数组的第二个元素交换位置；
4. 如此循环，直到整个数组有序。

**代码：**

```java
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
```



**对于长度为N的数组，选择排序需要大约N²/2次比较和N次交换。**



**特点：**

- **运行时间和输入无关**。内循环中，循环的目的是为了找到最小元素，循环一次并不能未第二次循环提供有用的信息。一个已经有序的数组，和一个所有元素相等的数组，和一个无序数组所用的时间一样长。
- **数据移动是最少的**。选择排序用了N次交换，即选择排序的交换次数和数组的大小是线性关系。其他算法都不具备这个特质。



### 插入排序

**思想：** 将数组的分为有序和无序两个部分，依次将无序部分的元素插入到有序部分的合适位置中。

1. 数组下标为0的第一个元素是有序的，外循环`i`从下标为1开始遍历无序部分；
2. 数组下标从`0`到`i-1`是有序部分，内循环`j`从下标`i`开始逆向遍历有序部分；
3. 内循环当`j`比`j-1`小时，交换两个元素；
4. 当a[i]和a[0]到a[i-1]中比它小的所有元素依次有序的交换。

**代码：**

```java
public static void sort(Comparable[] a) {
    // 遍历数组
    for (int i = 1; i < a.length; i++) {
        // 将当前位置的元素插入到前面的有序数组中
        for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
            exch(a, j, j - 1);
        }
    }
}
```

**特点：**

* 插入排序所需的时间取决于出入中元素的初始顺序，当序列的倒置数量越少，插入排序越快。

**优化：**

在内循环中，将较大元素都向右移动而不总是交换两个元素。这样访问数组的次数就能减半。

```java
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
```



### 冒泡排序

**思想：** 通过对待排序序列从前向后（从下标较小的元素开始），依次对相邻两个元素的值进行两两比较，若发现逆序则交换，使值较大的元素逐渐从前移向后部，就如果水底下的气泡一样逐渐向上冒。

**代码：**

```java
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
```



### 希尔排序

希尔排序是基于插入排序的改进。

**思想：** 使数组中任意间隔为`h`的元素都是有序的。这样的数组被称为`h`有序数组。一个`h`有序数组就是`h`个互相独立的有序数组编织在一起组成的一个数组。

对于h的取值：`whlie(h < N/3) h=3*h + 1;` `// 1, 4, 13, 40, 121, 364, 1093, ...`

对于每个h，用插入排序将h个子数组独立地排序。

希尔排序高效的原因：它权衡了子数组的规模和有序性。排序之初，各个子数组都很短，排序之后子数组都是部分有序的，这两种情况都很适合插入排序，插入排序的特点就是序列的倒置数量越少，插入排序越快。子数组部分有序的程度取决于递增序列的选择。

```java
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
```



### 归并排序

**思想：** 要将一个数组排序，可以先（递归地）将它分成两半本别排序，然后将结果归并起来。

**优点：** 保证将任意长度为N的数组排序所需时间和`NlogN`成正比。

**缺点：** 所需要的额外空间和N成正比。

**代码：**

自顶向下的归并排序

```java
public class MergeSort {
    private Comparable[] aux;
    
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }
    
    public static void sort(Comparable[] a, int lo, int hi) {
        if(lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }
    
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1, k;
        for(k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for(k = lo; k <= hi; k++) {
            if(i > mid) a[k] =  aux[j++];
            else if(j > hi) a[k] = aux[i++];
            else if(less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];   
        }
    }
}
```



自底向上的归并排序

先归并那些微型数组，然后再成对归并得到的子数组。先两两归并、再四四归并、再八八归并、以此类推。

```java
public static void sort(Comparable[] a) {
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
```



### 快速排序

**思想：** 快速排序的思想是分治的。它将一个数组分成两个子数组，将两部分独立的排序。

和归并排序相比： 归并排序将数组分成两个子数组分别排序，再将两个有序的子数组归并成一个数组以实现排序。

快速排序：当两个子数组都有序时，整个数组就自然有序了。

归并排序的递归调用在处理整个数组之前；快速排序的递归调用在处理整个数组之后。

快速排序的关键在于切分。找到一个切分元素，将数组分成两个子数组，前面部分的子数组都不大于切分元素，后面部分的子数组都不小于切分元素。

**特点：** 应用最广泛。实现简单。适用于各种不同的输入。一般应用中比其他排序算法都要快得多。他是原地排序。将长度为N的数组排序所需的时间和`NlogN`成正比。

**代码：** 

```java
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
```

 

## 查找

### 符号表

`public class ST<Key, Value>`

**API**

| 方法                           | 作用                                            | 默认实现                   |
| ------------------------------ | ----------------------------------------------- | -------------------------- |
| `ST()`                         | 创建一张符号表                                  |                            |
| `void put(Key key, Value val)` | 将键值对存入表中（若值为空则将键key从表中删除） |                            |
| `Value get(Key key)`           | 获取键Key对应的值（若键Key不存在则返回null）    |                            |
| `void delete(Key key)`         | 从表中删除键key（以及其对应的值）               | `put(key, null);`          |
| `boolean isEmpty()`            | 表是否为空                                      | `return size() == 0;`      |
| `boolean contains(Key key)`    | 键Key在表中是否有对应的值                       | `return get(key) != null;` |
| `int size()`                   | 表中键值对数量                                  |                            |
| `Iterable<Key> keys()`         | 表中的所有键的集合                              |                            |



### 有序符号表

`public class ST<Key extends Comparable<key>, Value>`

**API** 

| 方法                                 | 作用                                            | 默认实现                     |
| ------------------------------------ | ----------------------------------------------- | ---------------------------- |
| `ST()`                               | 创建一张符号表                                  |                              |
| `void put(Key key, Value val)`       | 将键值对存入表中（若值为空则将键key从表中删除） |                              |
| `Value get(Key key)`                 | 获取键Key对应的值（若键Key不存在则返回null）    |                              |
| `void delete(Key key)`               | 从表中删除键key（以及其对应的值）               | `put(key, null);`            |
| `boolean isEmpty()`                  | 表是否为空                                      | `return size() == 0;`        |
| `boolean contains(Key key)`          | 键Key在表中是否有对应的值                       | `return get(key) != null;`   |
| `int size()`                         | 表中键值对数量                                  |                              |
| `Key min()`                          | 最小的键                                        |                              |
| `Key max()`                          | 最大的键                                        |                              |
| `Key floor(Key key)`                 | 小于等于key的最大键                             |                              |
| `Key ceiling(Key key)`               | 大于等于key的最小键                             |                              |
| `int rank(Key key)`                  | 小于key的键的数量                               |                              |
| `Key select(int k)`                  | 排名为k的键                                     |                              |
| `void deleteMin()`                   | 删除最小的键                                    | `delete(min());`             |
| `void deleteMax()`                   | 删除最大的键                                    | `delete(max());`             |
| `int size(Key lo, Key hi)`           | [lo..hi] 之间键的数量                           | 见下面代码块                 |
| `Iterable<Key> keys(Key lo, Key hi)` | [lo..hi] 之间的所有键，已排序                   |                              |
| `Iterable<Key> keys()`               | 表中的所有键的集合                              | `return keys(min(), max());` |

```java
int size(Key lo, Key hi) {
    // 给定区间不合法 返回0
    if(hi.compareTo(lo) < 0)
        return 0;
    // 存在hi的Key
    else if(contains(hi))
        return rank(hi) - rank(lo) + 1;
    // 不存在hi的Key
    else 
        return rank(hi) - rank(lo);
}
```



### 顺序查找（基于无序链表）

```java
package com.danby.algorithms4.search;

public class SequentialSearchST<Key, Value> implements ST<Key, Value> {

    // 头节点
    private Node first;

    // 链表节点定义
    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    @Override
    public void put(Key key, Value value) {
        // 如果value为null，则删除key对应的节点
        if (value == null) {
            delete(key);
            return;
        }
        // 遍历链表，查找key对应的节点
        for (Node current = first; current != null; current = current.next) {
            // 如果找到key对应的节点，则更新节点的value
            if (key.equals(current.key)) {
                current.val = value;
                return;
            }
            // 如果没有找到key对应的节点，则创建新节点，并将其插入链表头部
            first = new Node(key, value, first);
        }

    }

    @Override
    public void delete(Key key) {
        Node current = first;
        Node previous = null;
        while (current != null) {
            // 命中key
            if (key.equals(current.key)) {
                if (previous == null) {
                    first = current.next;
                } else {
                    previous.next = current.next;
                }
                return;
            }
            previous = current;
            current = current.next;
        }
        //出循环说明未命中，直接自动返回
    }

    @Override
    public Value get(Key key) {
        for (Node current = first; current != null; current = current.next) {
            if (key.equals(current.key))
                return current.val;
        }
        return null;
    }

    @Override
    // 重写父类的方法，返回链表的大小
    public int size() {
        int count = 0;
        // 遍历链表，从第一个节点开始
        for (Node current = first; current != null; current = current.next)
            // 每遍历一个节点，计数器加一
            count++;
        // 返回链表的大小
        return count;
    }

    @Override
    public Iterable<Key> Keys() {
        return null;
    }
}
```



### 有序数组中的二分查找



```java
package com.danby.algorithms4.search;

public class BinarySearchST<Key extends Comparable<Key>, Value> implements SequenceST<Key, Value> {

    private Key[] keys;
    private Value[] values;
    private int N;

    BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
        N = capacity;
    }


    @Override
    public Key min() {
        return keys[0];
    }

    @Override
    public Key max() {
        return keys[N - 1];
    }

    @Override
    public Key floor(Key key) {
        return keys[rank(key) - 1];
    }

    @Override
    public Key ceiling(Key key) {
        return keys[rank(key) + 1];
    }

    @Override
    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0)
                hi = mid - 1;
            else if (cmp > 0)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }

    public int rank(Key key, int lo, int hi) {
        if (hi < lo) return lo;
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0) return rank(key, lo, mid - 1);
        else if (cmp > 0) return rank(key, mid + 1, hi);
        else return mid;
    }

    @Override
    public Key select(int k) {
        return null;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Override
    public void put(Key key, Value value) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    @Override
    public Value get(Key key) {
        if (isEmpty())
            return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
            return values[i];
        return null;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<Key> Keys() {
        return null;
    }
}
```

