package cn.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author xiakai
 * @create 2020-06-19 15:20
 */
public class BrokenBarrierExceptionTest {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("zhuxianchen");
        });
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(finalI);
                try {
                    cyclicBarrier.await();
                    System.out.println(finalI + " ok");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cyclicBarrier.reset();
        System.out.println("end");
    }

}
