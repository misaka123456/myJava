package cn.bingfa;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.IntUnaryOperator;

public class AtomicLongTest {

    static private AtomicLong count = new AtomicLong(0);

    static private void addOne() {
        count.getAndIncrement();
    }

    public static void main(String[] args) {

        AtomicInteger ai = new AtomicInteger(0);

        System.out.println(ai.compareAndSet(0, 10));
        System.out.println(ai);

        ai.getAndUpdate(new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                System.out.println(operand);
                return operand + 1;
            }
        });
        System.out.println(ai);
        System.out.println("----------");
        ai.updateAndGet(new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                System.out.println(operand);
                return operand + 1;
            }
        });
        System.out.println(ai);


//        Thread t1 = new Thread(() -> {
//
//            for (int i = 0; i < 100; i++) {
//                System.out.println("t1: " + count);
//                addOne();
//            }
//        });
//        Thread t2 = new Thread(() -> {
//
//            for (int i = 0; i < 100; i++) {
//                System.out.println("t2: " + count);
//                addOne();
//            }
//        });
//
//        t1.start();
//        t2.start();






    }

}
