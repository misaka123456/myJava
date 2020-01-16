package thread_base;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xiakai
 * @create 2020-01-16 09:46
 */
public class Test14_ThreadPoolParameter {

    public static void main(String[] args) {


        // 核心线程数、最大线程数、超时时间（超时后销毁，直至等于核心线程数）、超时时间单位
        // 阻塞队列（线程不够用后会进入阻塞队列，队列满后会报拒绝异常）、拒绝异常处理
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("拒绝");
            }
        });

        for (int i = 0; i < 22; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
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
