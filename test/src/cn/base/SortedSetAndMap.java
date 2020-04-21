package cn.base;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class SortedSetAndMap {

    public static void main(String[] args) {

        TreeSet<Integer> set = new TreeSet<>(Comparator.comparingInt(a -> -a));
        set.add(1);
        set.add(5);
        set.add(2);
        System.out.println(set);
        System.out.println(set.last());
        System.out.println(set.first());

        System.out.println("--------------------sortedMap---------------");

        TreeMap<Integer, Character> map = new TreeMap<>();
        map.put(1, 'a');
        map.put(5, 'c');
        map.put(2, 'b');
        System.out.println(map);
        Map.Entry<Integer, Character> last = map.lastEntry();
        System.out.println(last.getKey() + "  " + last.getValue());
    }
}
