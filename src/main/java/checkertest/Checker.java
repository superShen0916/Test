package checkertest;

/**
 * @Description: checker
 * @Author: shenpeng
 * @Date: 2019-06-15
 */
public class Checker {

    public boolean check() throws InterruptedException {
        boolean flag = false;
        Thread.sleep(5000);
        flag = true;
        Thread.sleep(2000);
        System.out.println(flag);
        return flag;
    }

}
