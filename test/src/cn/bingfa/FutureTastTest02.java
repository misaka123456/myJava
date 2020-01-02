package cn.bingfa;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTastTest02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> ft1 = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(222);
                return "111";
            }
        });

        new Thread(ft1).start();
        System.out.println(ft1.get());
    }


}
