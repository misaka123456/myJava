package model;

public class MyListByArray<E> {

    private Object[] arr;
    private int start;
    private int end;
    private int size;

    public MyListByArray(int num) {
        arr = new Object[num];
        start = -1;
        end = -1;
        size = 0;
    }

    public void push(E e) {
        if (size == arr.length) {
            throw new ArrayIndexOutOfBoundsException("out of size");
        } else {
            if (start == -1) {
                arr[++end] = e;
                start++;
            } else {
                if (end == arr.length - 1) {
                    end = 0;
                    arr[end] = e;
                } else {
                    arr[++end] = e;
                }
            }
            size++;
        }
    }

    public E pop() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("out of 0");
        } else {
            size--;
            if (start == arr.length - 1) {
                start = 0;
                return elementData(arr.length - 1);
            } else {
                return elementData(start++);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) arr[index];
    }

    @Override
    public String toString() {

        if (size == 0) {
            return "[]";
        }
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = start; ; i++) {
            b.append(String.valueOf(arr[i]));
            if (i == end) {
                b.append(']');
                break;
            }
            b.append(", ");
            if (i == arr.length - 1) {
                i = -1;
            }
        }
        return b.toString();

    }
}
