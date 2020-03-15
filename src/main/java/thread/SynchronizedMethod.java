package thread;

import java.util.List;
import java.util.concurrent.Callable;

import com.google.common.collect.Lists;
import com.playcrab.kos.common.utils.KOSTimeUtils;

/**
 * @Description: 测试两个syncronized方法操作同一个对象会不会有并发问题
 *               结论：当synchronized的是static方法时不会，锁的是类对象
 *               非静态方法时，锁的是类对象
 * 
 *               | 修饰的目标 | 实际锁住的对象 | 伪代码 |
 *               | ---- | --- | --- |
 *               | 实例方法 | 类的实例对象 |public synchronized void remove(){... }
 *               | 静态方法 | 类对象 |public static synchronized void
 *               remove(){...} | 实例对象 | 类的实例对象 |synchronized(this) {...}
 *               | class对象 | 类对象 |synchronized (RatedArena.class){...}
 *               | 任意实例对象obj | 实例对象obj | String s = "";<br>
 *               synchronized (s){...} |
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

    public static synchronized void remove() throws InterruptedException {
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
