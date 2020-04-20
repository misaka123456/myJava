package model;

import myTools.MyArrayTools;

import java.util.Collection;

public class MyLinkedNode<E> {

    private Object value;
    private MyLinkedNode next;

    public MyLinkedNode() {
    }

    public MyLinkedNode(E value) {
        this.value = value;
    }

    public MyLinkedNode(E value, MyLinkedNode next) {
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

    public void setNext(MyLinkedNode next) {
        this.next = next;
    }

    public E getValue() {
        return elementData(value);
    }

    public MyLinkedNode getNext() {
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
    private MyLinkedNode getLoopNode() {
        MyLinkedNode fast = this.next;
        MyLinkedNode slow = this;
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
        MyLinkedNode node = getLoopNode();
        if (node == null) {
            return 0;
        }
        int len = 1;
        MyLinkedNode n = node.next;
        while (n != node) {
            n = n.next;
            len++;
        }
        return len;
    }

    /**
     * 得到环的起点，没有环则返回null
     */
    public MyLinkedNode<E> getLoopStart() {
        int len = loopLength();
        if (len == -1) {
            return null;
        }
        MyLinkedNode start = this;
        MyLinkedNode node = start;
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
    public static <E> MyLinkedNode<E> build(E[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        return build(arr, -1);
    }

    /**
     * 根据对象数组构建链表，p代表环的其实节点在数组中的位置
     */
    public static <E> MyLinkedNode<E> build(E[] arr, int p) {
        MyLinkedNode<E> root = new MyLinkedNode<>(arr[0]);
        MyLinkedNode node = root;
        MyLinkedNode pNode = null;
        if (p == 0) {
            pNode = root;
        }
        for (int i = 1; i < arr.length; i++) {
            node.next = new MyLinkedNode<>(arr[0]);
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
    private static <E> MyLinkedNode<E> elementNode(MyLinkedNode n) {
        return (MyLinkedNode<E>) n;
    }

    /**
     * 根据集合对象构建链表
     */
    public static <E> MyLinkedNode<E> build(Collection<E> c) {
        return elementNode(build(c.toArray(new Object[0])));
    }

    /**
     * 根据集合对象构建链表
     */
    public static <E> MyLinkedNode<E> build(Collection<E> c, int p) {
        return elementNode(build(c.toArray(new Object[0]), p));
    }

    /**
     * 根据int类型数组构建链表
     */
    public static MyLinkedNode<Integer> build(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        return build(MyArrayTools.intToInteger(arr));
    }

    /**
     * 根据int类型数组构建链表
     */
    public static MyLinkedNode<Integer> build(int[] arr, int p) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        return build(MyArrayTools.intToInteger(arr), p);
    }
}
