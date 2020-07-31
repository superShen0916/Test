package thread;

import com.alipay.remoting.NamedThreadFactory;

import java.util.concurrent.*;

/**
 * @Description: CountDownLatch 和 CyclicBarrier 比较
 * 
 * 
 *               CountDownLatch可以实现类似计数器的功能，一个线程等待若干个其它线程完成后执行。
 *               CyclicBarrier是一组线程都在等待到达某一条件后，执行某一操作。
 * 
 * @Author: shenpeng
 * @Date: 2019-04-04
 */
public class CyclicBarrierCom {

    public static void main(String[] args) {
        /**
         * CyclicBarrier c=new CyclicBarrier( int parties,Runnable BarrierAction);
         * 会在cyclicBarrier.await();执行 parties 次后运行 BarrierAction()方法
         * 
         * ！！！---但是如果线程池大小不够，那么之前的线程都不会释放资源---！！！
         * 
         */

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {

            @Override
            public void run() {
                System.out.println("all done");
            }
        });

        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0, TimeUnit.NANOSECONDS,
                new LinkedBlockingQueue<>(), new NamedThreadFactory("pool"),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 5; i++) {
            executorService.submit(new task(cyclicBarrier));
        }

    }

    static class task implements Runnable {

        private final CyclicBarrier cyclicBarrier;

        task(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            try {
                cyclicBarrier.await(10L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
