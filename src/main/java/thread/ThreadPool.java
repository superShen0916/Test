package thread;

import java.util.concurrent.*;

public class ThreadPool {

    public static void main(String[] args) {
        CachedThreadPool();
        FixedThreadPool();
    }

    public static void FixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println("=" + Thread.currentThread().getName());
                        Thread.sleep(30000);
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
                        Thread.sleep(30000);
                        System.out.println("-----" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
