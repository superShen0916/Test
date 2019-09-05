package proxy.staticproxy;

/**
 * @Description: 测试类
 * @Author: shenpeng
 * @Date: 2019-09-05
 */
public class TestStaticProxy {

    public static void main(String[] args) {
        IUserDao target = new UserDao();
        UserDaoProxy proxy = new UserDaoProxy(target);
        proxy.save();
    }
}
