package model;

import java.util.Arrays;

public class MyStackByArray<E> {

    private Object[] arr;
    private int size;

    public MyStackByArray(int num) {
        this.arr = new Object[num];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) arr[index];
    }

    public int size() {
        return size;
    }

    public void push(E e) throws ArrayIndexOutOfBoundsException {
        if (size < arr.length - 1) {
            arr[size++] = e;
        } else {
            throw new ArrayIndexOutOfBoundsException("out of size");
        }
    }

    public E pop() {
        if (size != 0) {
            return elementData(--size);
        } else {
            throw new ArrayIndexOutOfBoundsException("out of 0");
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(arr[i]));
            if (i == size - 1) {
                b.append(']');
                return b.toString();
            }
            b.append(", ");
        }
    }
}
