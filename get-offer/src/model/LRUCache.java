package model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiakai
 * @create 2020-05-25 19:12
 */
public class LRUCache {

    Map<Integer, NodeList> map;
    NodeList head;
    NodeList tail;
    int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new NodeList(-1);
        tail = new NodeList(-1);
        head.next = tail;
        tail.pre = head;

    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        NodeList curNode = map.get(key);
        update(curNode);
        return curNode.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            NodeList curNode = map.get(key);
            curNode.val = value;
            update(curNode);
        } else {
            NodeList newNode = new NodeList(value);
            newNode.mapKey = key;
            map.put(key, newNode);
            insert(newNode, head);
            if (map.size() > capacity) {
                map.remove(tail.pre.mapKey);
                delete(tail.pre);
            }
        }
    }

    private void delete(NodeList node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void insert(NodeList node, NodeList pre) {
        node.next = pre.next;
        pre.next.pre = node;
        pre.next = node;
        node.pre = pre;
    }

    private void update(NodeList node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }


    static class NodeList {
        int mapKey;
        int val;
        NodeList pre;
        NodeList next;

        NodeList(int val) {
            this.val = val;
            pre = null;
            next = null;
        }
    }

}
