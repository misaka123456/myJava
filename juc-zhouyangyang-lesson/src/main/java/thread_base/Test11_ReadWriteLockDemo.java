package thread_base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xiakai
 * @create 2020-01-15 17:04
 */
public class Test11_ReadWriteLockDemo {

    public static void main(String[] args) {



        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final String s = String.valueOf(i);
            new Thread(() -> {
                myCache.put(s, s);
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            final String s = String.valueOf(i);
            new Thread(() -> {
                myCache.get(s);
            }).start();
        }


    }

}


class MyCache {

    ReadWriteLock lock = new ReentrantReadWriteLock();

    private volatile Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value) {
        // 写锁
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + " 开始写入");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + " 写入完成");
        lock.writeLock().unlock();
    }

    public void get(String key) {
        // 读锁
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + " 开始读取");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.get(key));
        System.out.println(Thread.currentThread().getName() + " 读取完成");
        lock.readLock().unlock();
    }



}