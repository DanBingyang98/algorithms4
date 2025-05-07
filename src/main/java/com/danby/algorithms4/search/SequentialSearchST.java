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
