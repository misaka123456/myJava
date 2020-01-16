package thread_base;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author xiakai
 * @create 2020-01-15 16:24
 */
public class Test10_SemaphoreDemo {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 占到车位");
                    try {
                        TimeUnit.SECONDS.sleep((int) (Math.random() * 5));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 离开车位");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

}
