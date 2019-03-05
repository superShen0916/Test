package test.exception;

/**
 * @Description: 测试能不能捕获到嵌套方法里面的异常 ------> 可以
 * @Author: shenpeng
 * @Date: 2019/1/26
 */
public class ThrowException {

    //    public static void main(String[] args) {
    //        try {
    //            method1();
    //        } catch (Exception e) {
    //            System.out.println("==========");
    //            // e.printStackTrace();
    //        }
    //        System.out.println(111);
    //
    //    }
    //
    //    public static void method1() {
    //        method2();
    //    }
    //
    //    public static void method2() {
    //        System.out.println(0 / 0);
    //    }

    public static void main(String[] args) {
        ExceptionMiddleClass exceptionMiddleClass = new ExceptionMiddleClass();
        try {

            exceptionMiddleClass.method1();
        } catch (Exception e) {
            System.out.println("---");
        }
        System.out.println(111);
    }

}
