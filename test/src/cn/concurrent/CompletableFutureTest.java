package cn.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {

    public static void main(String[] args) {
        // 任务 1：洗水壶 -> 烧开水
        // runAsync 调用的是 Runnable 的 run() 方法，没有返回值
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(()->{
            System.out.println("T1: 洗水壶...");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("T1: 烧开水...");
            sleep(15, TimeUnit.SECONDS);
        });
        // 任务 2：洗茶壶 -> 洗茶杯 -> 拿茶叶
        // supplyAsync 调用的是Supplier 接口的 get() 方法，有返回值
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(()->{
            System.out.println("T2: 洗茶壶...");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("T2: 洗茶杯...");
            sleep(2, TimeUnit.SECONDS);

            System.out.println("T2: 拿茶叶...");
            sleep(1, TimeUnit.SECONDS);
            return " 龙井 ";
        });
        // 任务 3：任务 1 和任务 2 完成后执行：泡茶
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) ->{
            System.out.println("T1: 拿到茶叶:" + tf);
            System.out.println("T1: 泡茶...");
            return " 上茶:" + tf;
        });
        // 等待任务 3 执行结果
        System.out.println(f3.join());
    }

    private static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}
