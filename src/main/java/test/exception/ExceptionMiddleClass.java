package test.exception;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2019/1/26
 */
public class ExceptionMiddleClass {

    public void method1() {
        //  try {
        ExceptionClass exceptionClass = new ExceptionClass();
        exceptionClass.exceptionMethod();
        //        } catch (Exception e) {
        //            System.out.println("-=-=-=-=-=");
        //        }

    }
}
