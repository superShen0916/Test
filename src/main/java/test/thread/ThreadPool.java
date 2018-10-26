package test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    public static void main(String[] args) {
        CachedThreadPool();
        FixedThreadPool();
    }

    public static void FixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println("=" + Thread.currentThread().getName());
                        Thread.sleep(1000);
                        System.out.println("-" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void CachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println("=====" + Thread.currentThread().getName());
                        Thread.sleep(1000);
                        System.out.println("-----" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
