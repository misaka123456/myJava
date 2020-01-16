package thread_base;

import java.util.concurrent.CountDownLatch;

public class Test8_CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch count = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 离开教室");
                count.countDown();
            }).start();
        }

        System.out.println("start");
        new Thread(() -> {
            try {
                count.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 关门");
        }).start();

        System.out.println("end");


    }

}
