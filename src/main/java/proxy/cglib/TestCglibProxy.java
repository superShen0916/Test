package proxy.cglib;

/**
 * @Description: 测试类
 * @Author: shenpeng
 * @Date: 2019-09-05
 */
public class TestCglibProxy {

    public static void main(String[] args) {
        UserDao target = new UserDao();
        UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
        proxy.save();
    }
}
