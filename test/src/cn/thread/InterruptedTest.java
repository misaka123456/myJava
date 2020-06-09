package cn.thread;

/**
 * @author xiakai
 * @create 2020-06-09 11:17
 */
public class InterruptedTest {

    public static void main(String[] args) {


        Thread main = Thread.currentThread();

        //1 静态方法，判断当前线程是否被中断，并且会重置中断状态为  未被中断
        System.out.println("当前线程被中断了1[false,会清除标志位]?"+Thread.interrupted());
        main.interrupt();
        //2 非静态方法，判断当前线程是否被中断，不会重置中断标志
        System.out.println("当前线程被中断了2[true,不会清除标志]?"+main.isInterrupted());

        //判断完后会把中断标志设为false
        System.out.println("当前线程被中断了3[true,没有清除标志]?" + Thread.interrupted());

        //已经被清除过中断标志了
        System.out.println("当前线程被中断了4[fasle,被清除标志了]?"+main.isInterrupted());

    }

}
