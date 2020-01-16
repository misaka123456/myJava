package thread_base;

import java.util.concurrent.*;

/**
 * @author xiakai
 * @create 2020-01-16 09:46
 */
public class Test14_ThreadPoolParameter {

    public static void main(String[] args) {


        // 核心线程数、
        // 最大线程数、
        // 超时时间（超时后销毁，直至等于核心线程数）、
        // 超时时间单位
        // 阻塞队列（线程不够用后会进入阻塞队列，队列满后会报拒绝异常）、
        // 默认线程工厂
        // 拒绝异常处理
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                3,
                5,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        // 线程拒绝机制
        // AbortPolicy 直接抛出默认RejectedExecutionException异常
        // CallerRunsPolicy 不会抛出异常、也不会抛弃任务，会将任务返回给调用者，从而降低新任务的流量
        // DiscardOldestPolicy 抛弃队列中等待最久的任务，然后把当前任务加入队列中
        // DiscardPolicy 不会抛出异常，直接抛弃任务，不做任何处理，这是最好的策略
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "  " + finalI);
            });
        }
        // 关闭线程池
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
        System.out.println("end");
    }

}
