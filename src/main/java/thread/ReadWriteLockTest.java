package thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-03-19
 */
public class ReadWriteLockTest {

    public static void main(String[] args) {
        ReadWriteLockDemo rwd = new ReadWriteLockDemo();
        //启动100个读线程
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {

                    rwd.get();
                }
            }).start();
        }

        //写线程
        new Thread(new Runnable() {

            @Override
            public void run() {
                rwd.set((int) (101));
            }
        }, "Write").start();
    }
}

class ReadWriteLockDemo {

    //模拟共享资源--Number
    private int number = 0;

    // 实际实现类--ReentrantReadWriteLock，默认非公平模式
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //读
    public void get() {
        //使用读锁
        readWriteLock.readLock().lock();
        try {

            Thread.sleep(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName() + " : " + number);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    //写
    public void set(int number) {
        readWriteLock.writeLock().lock();
        try {
            this.number = number;
            System.out.println(Thread.currentThread().getName() + " : " + number);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
