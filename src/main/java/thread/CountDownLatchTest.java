package thread;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * `CountDownLatch`可以看成是一个计数器,提供了await方法阻塞线程,
 * 等到CountDownLatch对象调用`countDownLatch.countDown()`使计数减到0了才会执行.
 * 
 * 输出：
 * 
 * Thread-1
 * Thread-3
 * Thread-4
 * Thread-2
 * Thread-0
 * main
 * Thread-5
 * Thread-8
 * Thread-7
 * Thread-6
 * Thread-9
 * 
 * 
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-07-01
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 10; i++) {
            new Thread(new Consumer(countDownLatch)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName());

    }

    static class Consumer implements Runnable {

        CountDownLatch countDownLatch;

        public Consumer(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
