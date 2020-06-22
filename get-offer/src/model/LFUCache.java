package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @author xiakai
 * @create 2020-06-22 10:02
 */
public class LFUCache {

    Map<Integer, Node> cache;
    Map<Integer, LinkedHashSet<Node>> freqMap;
    int min;
    int size;


    public LFUCache(int capacity) {
        size = capacity;
        min = 1;
        cache = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        LinkedHashSet<Node> fSet = freqMap.get(node.freq);
        fSet.remove(node);
        if (node.freq == min && fSet.size() == 0) {
            min = min + 1;
        }
        node.freq++;
        fSet = freqMap.get(node.freq);
        if (fSet == null) {
            fSet = new LinkedHashSet<>();
            freqMap.put(node.freq, fSet);
        }
        fSet.add(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            node = new Node(key, value);
            cache.put(key, node);
            LinkedHashSet<Node> fSet = freqMap.get(1);
            if (fSet == null) {
                fSet = new LinkedHashSet<>();
                freqMap.put(node.freq, fSet);
            }
            fSet.add(node);
            if (cache.size() > size) {
                fSet = freqMap.get(min);
                node = fSet.iterator().next();
                cache.remove(node.key);
                fSet.remove(node);
            }
            min = 1;
        } else {
            node.value = value;
            LinkedHashSet<Node> fSet = freqMap.get(node.freq);
            fSet.remove(node);
            if (node.freq == min && fSet.size() == 0) {
                min = min + 1;
            }
            node.freq++;
            fSet = freqMap.get(node.freq);
            if (fSet == null) {
                fSet = new LinkedHashSet<>();
                freqMap.put(node.freq, fSet);
            }
            fSet.add(node);
        }
    }


    static class Node {
        int key;
        int value;
        int freq;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
            freq = 1;
        }
    }

    public static void main(String[] args) {

        LFUCache cache = new LFUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
    }

}
