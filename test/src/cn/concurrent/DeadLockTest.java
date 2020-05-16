package cn.concurrent;

import java.util.concurrent.TimeUnit;

public class DeadLockTest {

    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        new Thread(() -> {
            synchronized (obj1) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2) {
                    System.out.println("t1 ok");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (obj2) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1) {
                    System.out.println("t2 ok");
                }
            }
        }).start();


    }

}
