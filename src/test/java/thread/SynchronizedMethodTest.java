package thread;

import java.util.List;
import java.util.concurrent.Callable;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.playcrab.kos.common.utils.KOSTimeUtils;

public class SynchronizedMethodTest {

    static List<String> list = Lists.newArrayList();

    @Test
    public void testSynchronizedMethod() throws InterruptedException {
        list.add(String.valueOf(KOSTimeUtils.getCurrentMills()));
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        SynchronizedMethodTest test = new SynchronizedMethodTest();
                        test.forLoop();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        SynchronizedMethodTest test = new SynchronizedMethodTest();
                        test.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        Thread.sleep(10000000L);
    }

    public synchronized void remove() throws InterruptedException {
        //    public static synchronized void remove() {
        System.out.println("remove start");
        list.remove(0);
        list.add(String.valueOf(KOSTimeUtils.getCurrentMills()));
        list.add(String.valueOf(KOSTimeUtils.getCurrentMills()));
        Thread.sleep(100);
        System.out.println("remove end");
    }

    public synchronized void forLoop() throws InterruptedException {
        try {
            for (String s : list) {
                Thread.sleep(100);
                System.out.println(" loop   " + s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class CallableThread implements Callable {

        @Override
        public Object call() throws Exception {
            return null;
        }
    }
}
