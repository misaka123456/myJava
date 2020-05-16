package cn.concurrent;

public class ThreadLocalTest02 {

    public static void main(String[] args) throws InterruptedException {

        ThreadLocal<Integer> tl = new ThreadLocal<>();
        tl.set(0);

        Thread t1 = new Thread(() -> {
            System.out.println(tl.get());
            tl.set(11);
            System.out.println(tl.get());
        });
        Thread t2 = new Thread(() -> {
            System.out.println(tl.get());
            tl.set(22);
            System.out.println(tl.get());
        });

        t1.start();
        t1.join();
        t2.start();

    }

}
