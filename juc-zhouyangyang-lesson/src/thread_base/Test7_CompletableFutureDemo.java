package thread_base;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Test7_CompletableFutureDemo {

    public static void main(String[] args) {

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("cf1");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "cf1";
        });
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("cf2");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "cf2";
        });

        CompletableFuture<String> cf3 = cf1.thenCombine(cf2, (f1, f2) -> {
            System.out.println("cf1 + cf2 = ");
            return "cf3";
        });

        System.out.println(cf3.join());
    }

}
