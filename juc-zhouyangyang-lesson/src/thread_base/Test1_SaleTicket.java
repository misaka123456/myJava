package thread_base;

public class Test1_SaleTicket {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 1; i <= 40; i++) {
                ticket.sale();
            }
        }, "线程A").start();

        new Thread(() -> {
            for (int i = 1; i <= 40; i++) {
                ticket.sale();
            }
        }, "线程B").start();

        new Thread(() -> {
            for (int i = 1; i <= 40; i++) {
                ticket.sale();
            }
        }, "线程C").start();


    }




}

class Ticket {
    private int number = 30;

    public synchronized void sale () {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出第 " + (number--) + " 张票，还剩 " + number);
        }
    }
}
