package forloop;

/**
 * @Description: for(; ;) 是个死循环
 * @Author: shenpeng
 * @Date: 2019-01-27
 */
public class ForLoop {

    public static void main(String[] args) throws InterruptedException {
        for (;;) {
            System.out.println(1);
            Thread.sleep(1000);
        }
    }
}
