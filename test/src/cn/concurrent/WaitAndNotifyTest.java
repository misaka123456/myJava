package cn.concurrent;

public class WaitAndNotifyTest {
    public static void main(String[] args) {
        Task task = new Task();

        new Thread(() -> {
            while(true){
                task.product();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while(true){
                task.product();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while(true){
                task.comsume();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    static class Task {

        private static final int MAX = 10;
        private int num;

        public synchronized void product() {
            while (num >= MAX) {
                System.out.println(Thread.currentThread().getName() + "库存数量达到最大值，停止生产。");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "正在生产商品，当前库存为：" + num);
            notifyAll();
        }

        public synchronized void comsume() {
            while (num <= 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + "没有商品了，消费者处于等待状态...");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "正在消费，当前库存为：" + num);
            notifyAll();
        }



    }
}


