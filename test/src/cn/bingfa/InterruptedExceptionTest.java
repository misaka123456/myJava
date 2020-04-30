package cn.bingfa;

public class InterruptedExceptionTest {

    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("interrupted ");
            }
        });

        t1.start();
        Thread.sleep(1000);

        t1.interrupt();

        System.out.println(t1.isInterrupted()); // 打断后只在第一次调用时返回true
        System.out.println(t1.isInterrupted()); // 之后都返回false
    }
}
