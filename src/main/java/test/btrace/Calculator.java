package test.btrace;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2019/1/9
 */
public class Calculator {

    private int c = 1;

    public int add(int a, int b) {
        try {
            //            Thread.sleep(5000);
            this.wait(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a + b;
    }

}
