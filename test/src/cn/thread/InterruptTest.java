package cn.thread;

/**
 * @author xiakai
 * @create 2020-06-09 11:06
 */
public class InterruptTest {

    public static void main(String[] args) {
//        Thread t1 = new Thread(()->{
//            try {
//                Thread.sleep(10_000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                System.out.println("睡眠被打断");
//            }
//            System.out.println("1");
//        });
//        t1.start();
//        //立马打断睡眠,让线程进入runnable状态
//        t1.interrupt();




        Thread mainThread = Thread.currentThread();
        Thread t1 = new Thread(()->{
            while (true){
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //打断main
                mainThread.interrupt();
            }
        });
        t1.start();

        try {
            System.out.println("程序停留在join");
            //这是由main执行的，所以是main join了。需要打断main
            t1.join();
            System.out.println("等待join执行完毕，join如果被打断则不会执行");
        } catch (InterruptedException e) {
            System.out.println("main被打断");
        }

    }

}
