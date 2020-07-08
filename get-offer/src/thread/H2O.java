package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author xiakai
 * @create 2020-07-07 13:32
 */
public class H2O {

    private Semaphore h;
    private Semaphore o;
    private CyclicBarrier cyclicBarrier;

    public H2O() {
        h = new Semaphore(2);
        o = new Semaphore(1);
        cyclicBarrier = new CyclicBarrier(3, () -> {
            h.release(2);
            o.release(1);
        });
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire();
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
    }


    public static void main(String[] args) {


    }

}
