package thread_base;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author xiakai
 * @create 2020-01-15 16:16
 */
public class Test9_CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cb = new CyclicBarrier(10, () -> {
            System.out.println("出现吧");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("傻逼");
        });

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集到：" + finalI + " 颗");
                try {
                    cb.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }



    }

}
