package checkertest;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2019-06-15
 */
public class Event {

    public static void main(String[] args) throws InterruptedException {
        CheckerManager.init();

        Run run = new Run();
        Run run2 = new Run();
        new Thread(run).start();
        Thread.sleep(6000);
        new Thread(run2).start();

    }

}

class Run implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                CheckerManager.checkers.get("1").check();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
