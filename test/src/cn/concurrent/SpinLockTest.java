package cn.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockTest {


    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "尝试获取锁");
        int i = 0;
        while (!atomicReference.compareAndSet(null, thread)) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i++ == 20) {
                System.out.println(thread.getName() + " 自旋得不到，进入锁升级模块。。");
            }
        }
        System.out.println(thread.getName() + "成功获取锁");
    }
    public void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "释放锁");
    }

    public static void main(String[] args) {
        SpinLockTest lock = new SpinLockTest();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();

            }).start();
        }
    }
}
