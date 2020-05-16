package cn.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {


    private int num = 3;

    private final Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();

    private final Condition notEmpty = lock.newCondition();

    void enq(String s) {
        lock.lock();
        try {
            while (num == 10) {
                notFull.await();
            }
            print(s);
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void deq(String s) {
        lock.lock();
        try {
            while (num == 0) {
                notEmpty.await();
            }
            print(s);
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void print(String s) {
        for (int i = 0; i < num; i++) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {

        ReentrantLockTest t = new ReentrantLockTest();

        t.enq("1");
        t.enq("2");




    }

}
