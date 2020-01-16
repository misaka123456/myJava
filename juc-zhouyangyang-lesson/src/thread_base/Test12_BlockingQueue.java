package thread_base;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author xiakai
 * @create 2020-01-15 19:07
 */
public class Test12_BlockingQueue {

    public static void main(String[] args) {

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        System.out.println(queue.add("111"));
        System.out.println(queue.add("222"));
        System.out.println(queue.add("333"));

        queue.remove();
        System.out.println(queue);

        System.out.println(queue.add("444"));
        System.out.println(queue);

        new LinkedList<String>().getFirst();



    }

}
