package cn.bingfa;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallerRunsPolicyTest {

    public static void main(String[] args) throws IOException {

        ThreadPoolExecutor threadPoolExecutor;
        threadPoolExecutor = new ThreadPoolExecutor(1, 1,
                0, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        System.out.println("1111");
        threadPoolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "running!");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 拒绝后交给main执行
        threadPoolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " running!");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 被阻塞
        System.out.println("2222");
        threadPoolExecutor.shutdown();
    }
}
