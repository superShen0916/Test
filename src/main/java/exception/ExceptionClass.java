package exception;

import java.io.FileReader;
import java.io.IOException;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2019/1/26
 */
public class ExceptionClass {

    public ExceptionClass() throws NullPointerException {
        try {
            FileReader fileReader = new FileReader("");
        } catch (IOException e) {
            //            throw e; 
        }

    }

    public void NPE() throws NoClassDefFoundError {

    }

    public void IOE() throws IOException {

    }

    public void test() {
        NPE();

        //这里编译器会报错，必须处理 IOException
        try {
            IOE();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exceptionMethod() {
        System.out.println(0 / 0);
    }
}
