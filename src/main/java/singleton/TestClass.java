package singleton;

/**
 * @Description: 测试类
 * @Author: shenpeng
 * @Date: 2019-09-30
 */
public class TestClass {

    public static void main(String[] args) {
        StaticInstance.instance.addNum();
        StaticInstance.instance.addNum();
        System.out.println(StaticInstance.instance.getNum());
        System.out.println(StaticInstance.getInstance().getNum());

    }

    class MyThread extends Thread {

        @Override
        public void run() {
            StaticInstance.instance.addNum();
        }
    }

}
