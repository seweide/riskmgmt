package com.haier.hairy.rmp.test;/**
 * @Classname MapTest
 * @Description TODO
 * @Date 2021/2/3 13:56
 * @Created by Seweide
 */
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname MapTest
 * @Description TODO
 * @Date 2021/2/3 13:56
 * @Created by Seweide
 */
public class MapTest {

    /**
     * Stripped-down version of helper class used in previous version,
     * declared for the sake of serialization compatibility
     */
    static class Segment<K,V> extends ReentrantLock implements Serializable {
        private static final long serialVersionUID = 2249069246763182397L;
        final float loadFactor;
        Segment(float lf) { this.loadFactor = lf; }
    }

    public static void main(String[] args) {
        Map<String,String> stringMap = new java.util.HashMap<>();
        //获得hash码
        stringMap.hashCode();
        //put值入map
        stringMap.put("刘德华","张惠妹");
        stringMap.put("张学友","大S");
        //map get()同步get，非线程安全方法
        String value1 = stringMap.get("刘德华");

        //线程安全的HashMap
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("刘德华","张惠妹");
        concurrentHashMap.put("张学友","大S");

        String value2 = concurrentHashMap.get("张学友");

    }

    /** For serialization compatibility. */
//    private static final ObjectStreamField[] serialPersistentFields = {
//            new ObjectStreamField("segments", ConcurrentHashMap.Segment[].class),
//            new ObjectStreamField("segmentMask", Integer.TYPE),
//            new ObjectStreamField("segmentShift", Integer.TYPE)
//    };

    /**
     * Map interFace 接口示例
     * @param <K>
     * @param <V>
     */
    interface Entry<K,V> {
        /**
         *返回与此条目对应的键。
         */
        K getKey();

        /**
         * 返回与此条目对应的值。 如果映射
         */
        V getValue();

        /**
         * R将与此条目对应的值替换为指定的
         *          值（可选操作）。 （写入地图。 的
         *          如果映射已
         *          从地图中删除（由<tt>删除</tt>操作）。
         */
        V setValue(V value);

        /**
         * Compares the specified object with this entry for equality.
         */
        @Override
        boolean equals(Object o);

        /**
         * Returns the hash code value for this map entry.  The hash code
         */
        @Override
        int hashCode();

        /**
         * Returns a comparator that compares {@link Map.Entry} in natural order on key.
         */
        public static <K extends Comparable<? super K>, V> Comparator<Map.Entry<K,V>> comparingByKey() {
            return (Comparator<Map.Entry<K, V>> & Serializable)
                    (c1, c2) -> c1.getKey().compareTo(c2.getKey());
        }

        /**
         * Returns a comparator that compares {@link Map.Entry} in natural order on value.
         */
        public static <K, V extends Comparable<? super V>> Comparator<Map.Entry<K,V>> comparingByValue() {
            return (Comparator<Map.Entry<K, V>> & Serializable)
                    (c1, c2) -> c1.getValue().compareTo(c2.getValue());
        }

        /**
         * Returns a comparator that compares {@link Map.Entry} by key using the given
         */
        public static <K, V> Comparator<Map.Entry<K, V>> comparingByKey(Comparator<? super K> cmp) {
            Objects.requireNonNull(cmp);
            return (Comparator<Map.Entry<K, V>> & Serializable)
                    (c1, c2) -> cmp.compare(c1.getKey(), c2.getKey());
        }

        /**
         * Returns a comparator that compares {@link Map.Entry} by value using the given
         */
        public static <K, V> Comparator<Map.Entry<K, V>> comparingByValue(Comparator<? super V> cmp) {
            Objects.requireNonNull(cmp);
            return (Comparator<Map.Entry<K, V>> & Serializable)
                    (c1, c2) -> cmp.compare(c1.getValue(), c2.getValue());
        }
    }

    /**
     * HashMap 内部类 实现
     * @param <K>
     * @param <V>
     */
    public class HashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable {

        @Override
        public Set<Entry<K, V>> entrySet() {
            return null;
        }

//        final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
//                       boolean evict) {
//            HashMap.Node<K,V>[] tab; HashMap.Node<K,V> p; int n, i;
//            if ((tab = table) == null || (n = tab.length) == 0)
//                n = (tab = resize()).length;
//            if ((p = tab[i = (n - 1) & hash]) == null)
//                tab[i] = newNode(hash, key, value, null);
//            else {
//                HashMap.Node<K,V> e; K k;
//                if (p.hash == hash &&
//                        ((k = p.key) == key || (key != null && key.equals(k))))
//                    e = p;
//                else if (p instanceof HashMap.TreeNode)
//                    e = ((HashMap.TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
//                else {
//                    for (int binCount = 0; ; ++binCount) {
//                        if ((e = p.next) == null) {
//                            p.next = newNode(hash, key, value, null);
//                            if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
//                                treeifyBin(tab, hash);
//                            break;
//                        }
//                        if (e.hash == hash &&
//                                ((k = e.key) == key || (key != null && key.equals(k))))
//                            break;
//                        p = e;
//                    }
//                }
//                if (e != null) { // existing mapping for key
//                    V oldValue = e.value;
//                    if (!onlyIfAbsent || oldValue == null)
//                        e.value = value;
//                    afterNodeAccess(e);
//                    return oldValue;
//                }
//            }
//            ++modCount;
//            if (++size > threshold)
//                resize();
//            afterNodeInsertion(evict);
//            return null;
//        }
    }

}
