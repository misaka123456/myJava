package thread_base;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Test6_CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> ft1 = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("callable启动");
            TimeUnit.SECONDS.sleep(1);
            return "ok";
        });
        new Thread(ft1).start();

        System.out.println(111);

        System.out.println(ft1.get());
//        new Thread(() -> {
//            try {
//                System.out.println(ft1.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }).start();


        System.out.println(222);

    }
}
