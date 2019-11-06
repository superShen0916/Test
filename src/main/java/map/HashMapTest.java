package map;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: hashMap死循环测试
 * @Author: shenpeng
 * @Date: 2019-11-06
 */
public class HashMapTest {

    static CountDownLatch latch = new CountDownLatch(5);

    public static void main(String[] args) throws InterruptedException {

        for (int j = 0; j < 10000; j++) {

            Test.map = new HashMap<Integer, Integer>(2);
            Test.at = new AtomicInteger();

            System.out.println("begin");

            int size = 10;
            latch = new CountDownLatch(size);

            for (int i = 0; i < size; i++) {
                Test test = new Test();
                test.setName("testThread-" + i);
                test.start();
            }
            latch.await();
            System.out.println("=====finish");

        }
        System.out.println("=============all finish");
        System.exit(1);

    }

}

class Test extends Thread {

    static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(2);

    static AtomicInteger at = new AtomicInteger();

    @Override
    public void run() {
        try {
            while (at.get() < 1000000) {
                map.put(at.get(), at.get());
                int value = at.incrementAndGet();
                if (value % 10000 == 0) {
                    System.out.println(value);
                }
                map.get(value);
                map.get(value - 1);
                map.get(value - 2);
                map.get(value + 1);
                map.get(value + 2);
            }
            System.out.println(map.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMapTest.latch.countDown();
    }
}
