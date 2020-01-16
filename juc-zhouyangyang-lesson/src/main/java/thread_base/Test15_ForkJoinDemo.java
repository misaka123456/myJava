package thread_base;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author xiakai
 * @create 2020-01-16 16:30
 */
public class Test15_ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyTask task = new MyTask(1, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(task);
        System.out.println(submit.get());


        forkJoinPool.shutdown();
    }

}


class MyTask extends RecursiveTask<Integer> {

    private static final Integer ADJUST_VALUE = 10;

    private final Integer begin;
    private final Integer end;
    private Integer result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
        result = 0;
    }

    @Override
    protected Integer compute() {
        if (end - begin <= ADJUST_VALUE) {
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        } else {
            int middle = (begin + end) >> 1;
            MyTask task1 = new MyTask(begin, middle);
            MyTask task2 = new MyTask(middle + 1, end);
            task1.fork();
            task2.fork();
            result = task1.join() + task2.join();
        }

        return result;
    }
}
