package test.thread;

public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {

            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("---===--");
                    try {
                        Thread.sleep(2000); //中断时会清除中断标志位
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}
