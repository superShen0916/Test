package thread;

/**
 * join 表示主线程会一直等待，知道目标线程执行完才会继续往后执行
 */
public class JoinTest {

    volatile static int i = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int a = 0; a < 10; a++) {
                    int count =0;
                    for (int j = 0; j < 10000000; j++) {
                        String s ="a"+"b"+j;
                    }
                    System.out.println(Thread.currentThread().getName() + "  --" + i++);
                }
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int a = 0; a < 10; a++) {
                    int count =0;
                    for (int j = 0; j < 10000000; j++) {
                        String s ="a"+"b"+j;
                    }
                    System.out.println(Thread.currentThread().getName() + "  --" + i++);
                }
            }
        });

        t1.start();
        //t1.join();
        t2.start();
        try {
            //t1.join();
            t2.join();
            System.out.println("----");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
