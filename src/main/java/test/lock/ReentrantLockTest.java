package test.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(createTask(), "FirstThread");
        Thread second = new Thread(createTask(), "SecondThread");
        first.start();
        second.start();
        Thread.sleep(6000);
        second.interrupt();
    }

    static private Runnable createTask() {
        return new Runnable() {

            public void run() {
                while (true) {
                    try {
                        //                        if (lock.tryLock(2000, TimeUnit.MILLISECONDS)) {
                        if (lock.tryLock()) {
                            //lock.lock();
                            try {
                                System.out.println("lock " + Thread.currentThread().getName());
                                Thread.sleep(4000);
                            } finally {
                                lock.unlock();
                                System.out.println("unlocked " + Thread.currentThread().getName());
                            }
                            break;
                        } else {
                            System.out
                                    .println("unable to lock " + Thread.currentThread().getName());
                        }
                    } catch (Exception e) {
                        System.out.println(Thread.currentThread().getName() + " is Interrupted"); //由于中断跳过了break，会继续请求锁
                    }
                }
            }
        };
    }
}
