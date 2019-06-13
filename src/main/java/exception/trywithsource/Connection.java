package exception.trywithsource;

/**
 * @Description: 继承AutoClosable接口的类
 * @Author: shenpeng
 * @Date: 2019-06-05
 */
public class Connection implements AutoCloseable {

    public void sendData() {
        System.out.println("sendData");
    }

    @Override
    public void close() throws Exception {
        System.out.println("close");
    }
}
