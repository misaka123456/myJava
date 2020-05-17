package model;

public class SkipList {

    private final int MAX_LEVEL = 16;
    private int curLevel;  // range from 1 to MAX_LEVEL
    private final Node head;

    public SkipList() {
        curLevel = 1;
        head = new Node(-1);
        head.next = new Node[MAX_LEVEL];
    }


    public boolean search(int val) {
        Node temp = head;
        for (int i = curLevel - 1; i >= 0; i--) {
            while (temp.next[i] != null && temp.next[i].val <= val) {
                if (temp.next[i].val == val) {
                    return true;
                } else {
                    temp = temp.next[i];
                }
            }
        }
        return false;
    }

    public void add(int val) {
        int level = randomLevel(0.5);
        if (curLevel < level) {
            curLevel = level;
        }

        Node node = new Node(val);
        node.next = new Node[level];
        Node temp = head;
        Node[] forward = new Node[level];

        for (int i = level - 1; i >= 0; i--) {
            while (temp.next[i] != null && temp.next[i].val < val) {
                temp = temp.next[i];
            }
            forward[i] = temp;
        }

        for (int i = 0; i < level; i++) {
            node.next[i] = forward[i].next[i];
            forward[i].next[i] = node;
        }
    }

    public boolean erase(int val) {
        Node temp = head;
        Node[] forward = new Node[curLevel];

        for (int i = curLevel - 1; i >= 0; i--) {
            while (temp.next[i] != null && temp.next[i].val < val) {
                temp = temp.next[i];
            }
            forward[i] = temp;
        }
        if (temp.next[0] == null || temp.next[0].val != val) {
            return false;
        }
        for (int i = 0; i < curLevel; i++) {
            if (forward[i].next[i] != null && forward[i].next[i].val == val) {
                forward[i].next[i] = forward[i].next[i].next[i];
            } else {
                break;
            }
        }
        while (curLevel > 1 && head.next[curLevel - 1] == null) {
            curLevel--;
        }
        return true;
    }

    private int randomLevel(double p) {
        int level = 1;
        while (Math.random() < p && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    class Node {
        int val;
        Node[] next;
        Node(int val) {
            this.val = val;
        }
    }
}
