package thread_base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test3_ConditionAndLock {

    private static AirConditionerByLock air = new AirConditionerByLock();


    public static void main(String[] args) {


        threadIncrement(15, "A1");
        threadIncrement(18, "A2");
        threadIncrement(17, "A3");
        threadDecrement(20, "B1");
        threadDecrement(20, "B2");
        threadDecrement(10, "B3");

    }


    private static void threadIncrement(int count, String name) {
        new Thread(() -> {
            for (int i = 0; i < count; i++) {
                air.increment();
            }
        }, name).start();
    }
    private static void threadDecrement(int count, String name) {
        new Thread(() -> {
            for (int i = 0; i < count; i++) {
                air.decrement();
            }
        }, name).start();
    }

}

class AirConditionerByLock {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + " increment: " + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + " decrement: " + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
