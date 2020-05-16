package cn.concurrent;

import java.util.concurrent.atomic.AtomicLong;

public class ThreadLocalTest {

    private static final AtomicLong id = new AtomicLong(0);

    private static int i = 0;

    // 定义 ThreadLocal 变量
    private static final ThreadLocal<Long>
            tl = ThreadLocal.withInitial(
            id::getAndIncrement);
    // 此方法会为每个线程分配一个唯一的 Id
    private static long get(){
        return tl.get();
    }

    private static final ThreadLocal<Integer>
            t2 = ThreadLocal.withInitial(
            () -> i++);
    // 此方法会为每个线程分配一个唯一的 Id
    private static long get2(){
        return tl.get();
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "  " + ThreadLocalTest.get2());
            System.out.println(Thread.currentThread().getName() + "  " + ThreadLocalTest.get2());
            System.out.println(Thread.currentThread().getName() + "  " + ThreadLocalTest.get2());
        });

        Thread t2 = new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "  " + ThreadLocalTest.get2());
            System.out.println(Thread.currentThread().getName() + "  " + ThreadLocalTest.get2());
            System.out.println(Thread.currentThread().getName() + "  " + ThreadLocalTest.get2());
        });
        t1.start();
        t2.start();




    }

}


