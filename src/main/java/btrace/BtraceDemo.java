package btrace;

import java.util.Random;

/**
 * @Description: btrace测试类，add方法每隔5秒对a、b两个数进行相加
 * @Author: shenpeng
 * @Date: 2019/1/9
 */

public class BtraceDemo {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Random random = new Random();
        while (true) {
            System.out.println(calculator.add(random.nextInt(10), random.nextInt(10)));
        }
    }
}
