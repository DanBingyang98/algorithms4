package com.danby.algorithms4.search;

public interface ST<Key , Value> {
    void put(Key key, Value value);

    Value get(Key key);

    // 删除指定键的元素
    default void delete(Key key) {
        put(key, null);
    }

    default boolean contains(Key key) {
        return get(key) != null;
    }

    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    Iterable<Key> Keys();
}
