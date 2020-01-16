package thread_base;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author xiakai
 * @create 2020-01-16 20:21
 */
public class Test16_CompletableFuture {

    public static void main(String[] args) {

        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
            System.out.println("无返回");
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("返回");
            return "result";
        });

//        CompletableFuture<Object> task3 = task1.thenCombine(task2, (__, s) -> {
//            System.out.println(s + "  OK");
//            return "end";
//        });
//        System.out.println(task3.join());


        System.out.println(task2.whenComplete((__, throwable) -> {
            System.out.println("task4 OK");
        }).join());
//        System.out.println(task4.join());


    }

}
