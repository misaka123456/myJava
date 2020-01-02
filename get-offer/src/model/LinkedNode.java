package model;

import myTools.MyArrayTools;

import java.util.Collection;

public class LinkedNode<E> {

    private Object value;
    private LinkedNode next;

    public LinkedNode() {
    }

    public LinkedNode(E value) {
        this.value = value;
    }

    public LinkedNode(E value, LinkedNode next) {
        this.value = value;
        this.next = next;
    }

    @SuppressWarnings("unchecked")
    private E elementData(Object o) {
        return (E) o;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public void setNext(LinkedNode next) {
        this.next = next;
    }

    public E getValue() {
        return elementData(value);
    }

    public LinkedNode getNext() {
        return next;
    }

    /**
     * 是否有环
     */
    public boolean isLoop() {
        return getLoopNode() != null;
    }

    /**
     * 得到环中任意一个节点，不存在环则返回null
     */
    private LinkedNode getLoopNode() {
        LinkedNode fast = this.next;
        LinkedNode slow = this;
        while (fast != null) {
            if (fast == slow) {
                return fast;
            }
            fast = fast.next;
            if (fast == null) {
                break;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return null;
    }

    /**
     * 获取环的长度，没有环则返回-1
     */
    public int loopLength() {
        LinkedNode node = getLoopNode();
        if (node == null) {
            return 0;
        }
        int len = 1;
        LinkedNode n = node.next;
        while (n != node) {
            n = n.next;
            len++;
        }
        return len;
    }

    /**
     * 得到环的起点，没有环则返回null
     */
    public LinkedNode<E> getLoopStart() {
        int len = loopLength();
        if (len == -1) {
            return null;
        }
        LinkedNode start = this;
        LinkedNode node = start;
        while (len-- > 0) {
            node = node.next;
        }
        while (start != node) {
            start = start.next;
            node = node.next;
        }
        return elementNode(start);
    }

    /**
     * 根据对象数组构建链表
     */
    public static <E> LinkedNode<E> build(E[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        return build(arr, -1);
    }

    /**
     * 根据对象数组构建链表，p代表环的其实节点在数组中的位置
     */
    public static <E> LinkedNode<E> build(E[] arr, int p) {
        LinkedNode<E> root = new LinkedNode<>(arr[0]);
        LinkedNode node = root;
        LinkedNode pNode = null;
        if (p == 0) {
            pNode = root;
        }
        for (int i = 1; i < arr.length; i++) {
            node.next = new LinkedNode<>(arr[0]);
            node = node.next;
            if (p == i) {
                pNode = node;
            }
        }
        if (pNode != null) {
            node.next = pNode;
        }
        return root;
    }

    @SuppressWarnings("unchecked")
    private static <E> LinkedNode<E> elementNode(LinkedNode n) {
        return (LinkedNode<E>) n;
    }

    /**
     * 根据集合对象构建链表
     */
    public static <E> LinkedNode<E> build(Collection<E> c) {
        return elementNode(build(c.toArray(new Object[0])));
    }

    /**
     * 根据集合对象构建链表
     */
    public static <E> LinkedNode<E> build(Collection<E> c, int p) {
        return elementNode(build(c.toArray(new Object[0]), p));
    }

    /**
     * 根据int类型数组构建链表
     */
    public static LinkedNode<Integer> build(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        return build(MyArrayTools.intToInteger(arr));
    }

    /**
     * 根据int类型数组构建链表
     */
    public static LinkedNode<Integer> build(int[] arr, int p) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        return build(MyArrayTools.intToInteger(arr), p);
    }
}
