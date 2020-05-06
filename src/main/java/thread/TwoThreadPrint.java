package thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description:两个线程实现输出12A34B56C....5152Z这个字符串
 * @Author: shenpeng
 * @Date: 2020-03-19
 */
public class TwoThreadPrint {

    static volatile int step = 0;

    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<>());

        Object lock = new Object();
        StringBuilder sb = new StringBuilder();
        Runnable runnable1 = new Runnable() {

            @Override
            public void run() {
                while (step < 51) {
                    synchronized (lock) {
                        if ((step % 2) == 1) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        sb.append(step + 1);
                        sb.append(step + 2);
                        step++;
                        System.out.println(Thread.currentThread().getName() + "   " + step);
                        lock.notify();

                    }
                }
            }
        };

        Runnable runnable2 = new Runnable() {

            @Override
            public void run() {
                while (step < 52) {
                    synchronized (lock) {
                        if ((step % 2) == 0) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        sb.append((char) ('A' + (step / 2)));
                        step++;
                        System.out.println(Thread.currentThread().getName() + "   " + step);
                        lock.notify();

                    }
                }
            }
        };

        Thread thread1 = new Thread(runnable1, "t1");
        Thread thread2 = new Thread(runnable2, "t2");
        thread1.start();
        thread2.start();
        while (step < 52) {

        }
        System.out.println(sb.toString());
    }
}
