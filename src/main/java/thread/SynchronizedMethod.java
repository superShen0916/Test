package thread;

import java.util.List;
import java.util.concurrent.Callable;

import com.google.common.collect.Lists;
import com.playcrab.kos.common.utils.KOSTimeUtils;

/**
 * @Description: 测试两个syncronized方法操作同一个对象会不会有并发问题
 *               结论：不会，synchronized表示这个方法要获得锁才会执行
 * @Author: shenpeng
 * @Date: 2019-09-04
 */
public class SynchronizedMethod {

    static List<String> list = Lists.newArrayList();

    public static void main(String[] args) {
        list.add(String.valueOf(KOSTimeUtils.getCurrentMills()));
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        forLoop();
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
                        remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    public static void remove() throws InterruptedException {
        //    public static synchronized void remove() {
        System.out.println("remove start");
        list.remove(0);
        list.add(String.valueOf(KOSTimeUtils.getCurrentMills()));
        list.add(String.valueOf(KOSTimeUtils.getCurrentMills()));
        Thread.sleep(100);
        System.out.println("remove end");
    }

    public static synchronized void forLoop() throws InterruptedException {
        for (String s : list) {
            Thread.sleep(100);
            System.out.println(" loop   " + s);
        }
    }

    class CallableThread implements Callable {

        @Override
        public Object call() throws Exception {
            return null;
        }
    }

}
