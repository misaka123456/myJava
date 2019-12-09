package cn.bingfa;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTaskTest {


    public static void main(String[] args) {

        ForkJoinPool fjp = new ForkJoinPool(4);

        Fibonacci fib = new Fibonacci(10);
        // 启动分治任务
        Integer i = fjp.invoke(fib);
        System.out.println(i);

    }


}

class Fibonacci extends RecursiveTask<Integer> {

    final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {

        if (n <= 1) {
            return n;
        }
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();
    }
}
