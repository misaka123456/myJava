package cn;


import java.util.concurrent.*;

public class TestMain {

    public static void main(String[] args) throws InterruptedException {



        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        
        for (int i = 0; i < 5; i++) {
            pool.execute(() -> {
                System.out.println(pool.getPoolSize());
                System.out.println(pool.getLargestPoolSize());
                System.out.println(pool.getTaskCount());
                System.out.println(pool.getCompletedTaskCount());
                System.out.println("----------");
            });
            Thread.sleep(1000);
        }

        System.out.println(pool.getPoolSize());
        System.out.println(pool.getLargestPoolSize());
        System.out.println(pool.getTaskCount());
        System.out.println(pool.getCompletedTaskCount());
        System.out.println(pool.getActiveCount());
        pool.shutdown();
    }






}

