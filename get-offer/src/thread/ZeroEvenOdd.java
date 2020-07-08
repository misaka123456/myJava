package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ZeroEvenOdd {
    private int n;
    private Lock lock;
    private Condition conditionZero;
    private Condition conditionEven;
    private Condition conditionOdd;
    private int stat = 0;

    public ZeroEvenOdd(int n) {
        this.n = n;
        lock = new ReentrantLock();
        conditionZero = lock.newCondition();
        conditionEven = lock.newCondition();
        conditionOdd = lock.newCondition();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                printNumber.accept(0);
                if (i % 2 == 1) {
                    stat = 2;
                    conditionEven.signal();
                } else {
                    stat = 1;
                    conditionOdd.signal();
                }
                conditionZero.await();
            } finally {
                lock.unlock();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i = i + 2) {
            lock.lock();
            try {
                while (stat != 2) {
                    conditionEven.await();
                }
                printNumber.accept(i);
                stat = 0;
                conditionZero.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i = i + 2) {
            lock.lock();
            try {
                while (stat != 1) {
                    conditionOdd.await();
                }
                printNumber.accept(i);
                stat = 0;
                conditionZero.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    static class IntConsumer {
        public IntConsumer() {

        }

        public void accept(int x) {
            System.out.println(x);
        }
    }


    public static void main(String[] args)  {

        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
        IntConsumer intConsumer = new IntConsumer();
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

