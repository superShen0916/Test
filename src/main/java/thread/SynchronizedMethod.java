package thread;

import com.google.common.collect.Lists;
import com.playcrab.kos.common.utils.KOSTimeUtils;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Description: 测试两个syncronized方法操作同一个对象会不会有并发问题
 *               结论：当synchronized的是static方法时不会，锁的是类对象
 *               非静态方法时，锁的是类对象
 * 
 *               也就是，对于非静态方法，如果是同一个synchronizedMethod对象调用，因为同一个对象只有一个mointor，所以回产生锁竞争，没有并发问题。
 *               如果是不同对象，比如在调用的时候 new
 *               SynchronizedMethod().remove();这种会有并发问题。
 *               如果又一个方法没有被synchronized修饰，那它就不需要获取monitor锁，会有并发问题。
 * 
 * 
 *               | 修饰的目标 | 实际锁住的对象 | 伪代码 |
 *               | ---- | --- | --- |
 *               | 实例方法 | 类的实例对象 |public synchronized void remove(){... }
 *               | 静态方法 | 类对象 |public static synchronized void
 *               remove(){...} | 实例对象 | 类的实例对象 |synchronized(this) {...}
 *               | class对象 | 类对象 |synchronized (RatedArena.class){...}
 *               | 任意实例对象obj | 实例对象obj | String s = "";<br>
 *               synchronized (s){...} |
 * 
 *               //test
 * @Author: shenpeng
 * @Date: 2019-09-04
 */
public class SynchronizedMethod {

    static List<String> list = Lists.newArrayList();

    public static void main(String[] args) {
        SynchronizedMethod synchronizedMethod = new SynchronizedMethod();

        list.add(String.valueOf(KOSTimeUtils.getCurrentMills()));
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        //                        new SynchronizedMethod().remove();
                        synchronizedMethod.remove();
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
                        //                        new SynchronizedMethod().forLoop();
                        synchronizedMethod.forLoop();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public synchronized void remove() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " remove start");
        list.remove(0);
        // list.add(String.valueOf(KOSTimeUtils.getCurrentMills()));
        Thread.sleep(5000);
        list.add(String.valueOf(KOSTimeUtils.getCurrentMills()));
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + " remove end");
    }

    public synchronized void forLoop() throws InterruptedException {
        System.out.println(list.size());
        for (String s : list) {
            System.out.println(" loop   " + s);
            Thread.sleep(1000);
        }
    }

    class CallableThread implements Callable {

        @Override
        public Object call() throws Exception {
            return null;
        }
    }

}
