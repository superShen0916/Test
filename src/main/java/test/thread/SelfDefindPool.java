package test.thread;

import java.util.concurrent.*;

import com.alipay.remoting.NamedThreadFactory;

/**
 * @Description: 自定义线程池
 * @Author: shenpeng
 * @Date: 2019-04-04
 */
public class SelfDefindPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * cachedThreadPool
         */
        ExecutorService cachedThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60,
                TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
                new NamedThreadFactory("CachedThread"), new ThreadPoolExecutor.AbortPolicy());

        int cpuNum = Runtime.getRuntime().availableProcessors();

        /**
         * fixedThreadPool
         */
        ExecutorService fixedThreadPool = new ThreadPoolExecutor(cpuNum + 1, cpuNum + 1, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
                new NamedThreadFactory("FixedThread"), new ThreadPoolExecutor.AbortPolicy());

        /**
         * singleThreadPool
         */
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1, 0, TimeUnit.NANOSECONDS,
                new LinkedBlockingQueue<>(), new NamedThreadFactory("SingleThread"),
                new ThreadPoolExecutor.AbortPolicy());

        /**
         * scheduledThreadPool
         */
        ExecutorService scheduledThreadPool = new ThreadPoolExecutor(cpuNum + 1, Integer.MAX_VALUE,
                0, TimeUnit.NANOSECONDS, new DelayQueue(),
                new NamedThreadFactory("ScheduledThread"), new ThreadPoolExecutor.AbortPolicy());

        ScheduledThreadPoolExecutor scheduledExecutorService = new ScheduledThreadPoolExecutor(2,
                new NamedThreadFactory("schedule"), new ThreadPoolExecutor.AbortPolicy());

        Runnable task = new Task();
        /**
         * scheduleAtFixedRate 从上一次任务开始执行时 根据period计算下次开始执行时间
         */
        // scheduledExecutorService.scheduleAtFixedRate(task, 3, 4, TimeUnit.SECONDS);
        /**
         * scheduleWithFixedDelay 从上一次任务结束的时候 根据delay计算下次任务开始的执行时间
         */
        //scheduledExecutorService.scheduleWithFixedDelay(task, 3, 4, TimeUnit.SECONDS);
        try {
            scheduledExecutorService.scheduleWithFixedDelay(task, 1, 1, TimeUnit.SECONDS);

        } catch (Exception e) {
            System.out.println(11);
        }

    }

    static class Task implements Runnable {

        @Override
        public void run() {
            Thread.currentThread()
                    .setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

                        @Override
                        public void uncaughtException(Thread t, Throwable e) {
                            System.out.println("exception");
                        }
                    });

            System.out.println("---");
            System.out.println(1 / 0);
            System.out.println(Thread.currentThread().getName());

        }
    }
}
