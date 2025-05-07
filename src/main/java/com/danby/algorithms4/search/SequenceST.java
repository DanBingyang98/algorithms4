package com.danby.algorithms4.search;

import com.sun.jdi.Value;

import java.security.Key;

public interface SequenceST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {
    Key min();

    Key max();

    Key floor(Key key);

    Key ceiling(Key key);

    int rank(Key key);

    Key select(int k);

    default void deleteMin() {
        delete(min());
    }

    default void deleteMax() {
        delete(max());
    }

    default int size(Key lo, Key hi) {
        // 给定区间不合法 返回0
        if (hi.compareTo(lo) < 0)
            return 0;
            // 存在hi的Key
        else if (contains(hi))
            return rank(hi) - rank(lo) + 1;
            // 不存在hi的Key
        else
            return rank(hi) - rank(lo);
    }

    Iterable<Key> keys(Key lo, Key hi);
}
