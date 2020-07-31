package thread;

import java.util.concurrent.Semaphore;

/**
 * 
 * Semaphore信号量用来控制同时访问某个资源的数量,或者同时执行某个指定操作的数量.
 * 
 * @Description: 信号量
 * @Author: shenpeng
 * @Date: 2020-07-01
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);

        //会5个一组一起出现,"资源"空闲之后下5个线程才会执行
        for (int i = 0; i < 20; i++) {
            new Thread(new Consumer(semaphore)).start();
        }
    }

    static class Consumer implements Runnable {

        Semaphore semaphore;

        public Consumer(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
