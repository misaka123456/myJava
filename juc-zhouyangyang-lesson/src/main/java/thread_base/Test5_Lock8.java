package thread_base;

import java.util.concurrent.TimeUnit;

class Phone {

    synchronized static void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("send mail");
    }

    synchronized void sendMSG() {
        System.out.println("send message");
    }

    void hello() {
        System.out.println("hello");
    }
}


public class Test5_Lock8 {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();

        new Thread(Phone::sendEmail).start();

        Thread.sleep(100);
        new Thread(phone::sendMSG).start();
        new Thread(phone::hello).start();





    }

}
