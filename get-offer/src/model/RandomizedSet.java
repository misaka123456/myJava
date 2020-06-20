package model;

import java.util.*;

/**
 * @author xiakai
 * @create 2020-06-17 19:37
 */
public class RandomizedSet {

    Map<Integer, Integer> map;
    List<Integer> list;
    int size;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        size = 0;
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        Integer i = map.get(val);
        if (i != null) {
            return false;
        }
        if (size == list.size()) {
            list.add(val);
        } else {
            list.set(size, val);
        }
        map.put(val, size);
        size++;
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        Integer i = map.remove(val);
        if (i == null) {
            return false;
        }
        size--;
        if (i != size) {
            list.set(i, list.get(size));
            map.put(list.get(i), i);
        }
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get((int) (Math.random() * size));
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.remove(0);
        randomizedSet.remove(0);
        randomizedSet.insert(0);
        randomizedSet.remove(0);
        System.out.println(randomizedSet.insert(0));
        System.out.println(randomizedSet.getRandom());
    }

}
