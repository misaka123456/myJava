package thread_base;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xiakai
 * @create 2020-01-15 19:40
 */
public class Test13_ThreadPoolDemo {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                threadPool.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":  " + finalI);
                });
            };
            System.out.println("ok!");
        } finally {
            threadPool.shutdown();
            System.out.println("shutdown complete");
        }
        System.out.println("end!");

    }

}
