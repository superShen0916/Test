package test.thread;

public class JoinTest {

    volatile static int i = 0;

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int a = 0; a < 10; a++) {

                    System.out.println(Thread.currentThread().getName() + "  --" + i++);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int a = 0; a < 10; a++) {

                    System.out.println(Thread.currentThread().getName() + "  --" + i++);
                }
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
