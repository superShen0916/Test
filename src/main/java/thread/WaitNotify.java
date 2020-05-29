package thread;

/**
 * @Description: wait 和 notify
 * @Author: shenpeng
 * @Date: 2020-05-30
 */
public class WaitNotify {

    public static void main(String[] args) throws InterruptedException {
        WaitNotify waitNotify = new WaitNotify();

        Thread thread1 = new Thread() {

            @Override
            public void run() {
                System.out.println(1);
                try {
                    synchronized (waitNotify) {
                        waitNotify.wait();
                    }
                    System.out.println(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread2 = new Thread() {

            @Override
            public void run() {
                System.out.println(2);
                try {
                    synchronized (waitNotify) {
                        waitNotify.wait();
                    }
                    System.out.println(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread1.start();
        thread2.start();
        //        synchronized (waitNotify) {
        //            waitNotify.wait();
        //        }
        Thread.sleep(1000);
        synchronized (waitNotify) {
            waitNotify.notify();
        }
        System.out.println("notify 1");
        Thread.sleep(5000);
        waitNotify.notify();
    }

}
