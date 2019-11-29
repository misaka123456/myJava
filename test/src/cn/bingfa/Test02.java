package cn.bingfa;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test02 {

    private String[] strArr = new String[10];

    private int num = 0;

    private final Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();

    private final Condition notEmpty = lock.newCondition();

    void enq(String s) {
        lock.lock();
        try {
            while (num == 10) {
                notFull.await();
            }
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void deq() {
        lock.lock();
        try {
            while (num == 0) {
                notEmpty.await();
            }
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        for (int i = 0; i < num; i++) {
            System.out.println(strArr[i]);
        }
    }

    public static void main(String[] args) {

        Test02 t = new Test02();

        t.enq("dszf");
        t.print();



    }

}
