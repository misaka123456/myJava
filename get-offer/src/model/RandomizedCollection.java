package model;

import java.util.*;

class RandomizedCollection {

    Map<Integer, TreeSet<Integer>> map;
    List<Integer> list;
    int size;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
        size = 0;
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if (size == list.size()) {
            list.add(val);
        } else {
            list.set(size, val);
        }
        boolean flag = false;
        TreeSet<Integer> set = map.get(val);
        if (set == null) {
            set = new TreeSet<>();
            map.put(val, set);
            flag = true;
        }
        set.add(size);
        size++;
        return flag;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        TreeSet<Integer> set = map.get(val);
        if (set == null) {
            return false;
        } else {
            size--;
            int i = set.pollLast();
            if (set.isEmpty()) {
                map.remove(val);
            }
            if (i != size) {
                TreeSet<Integer> other = map.get(list.get(size));
                list.set(i, list.get(other.pollLast()));
                other.add(i);
            }
            return true;
        }
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get((int) (Math.random() * size));
    }


    public static void main(String[] args) {
        RandomizedCollection randomizedCollection = new RandomizedCollection();



        System.out.println(randomizedCollection.insert(10));
        System.out.println(randomizedCollection.insert(10));
        System.out.println(randomizedCollection.insert(20));
        System.out.println(randomizedCollection.insert(20));
        System.out.println(randomizedCollection.insert(30));
        System.out.println(randomizedCollection.insert(30));
        randomizedCollection.remove(10);
        randomizedCollection.remove(10);
        randomizedCollection.remove(30);
        randomizedCollection.remove(30);
        System.out.println(randomizedCollection.getRandom());

    }
}


