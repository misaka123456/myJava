package thread_base;

class AirConditioner {
    private int number = 0;

    public synchronized void add() throws InterruptedException {
        // 使用while不用if 防止虚假唤醒，即每次唤醒后都需要重新判断一下
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + " add: " + number);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + " decrement: " + number);
        this.notifyAll();
    }
}

public class Test2_WaitNotify {
    public static void main(String[] args) {

        AirConditioner air = new AirConditioner();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    air.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    air.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    air.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A2").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    air.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B2").start();
    }
}
