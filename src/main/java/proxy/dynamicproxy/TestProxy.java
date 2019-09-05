package proxy.dynamicproxy;

/**
 * @Description: 测试类
 * @Author: shenpeng
 * @Date: 2019-09-05
 */
public class TestProxy {

    public static void main(String[] args) {

        //        IUserDao target = new UserDao();
        //        UserDaoProxy proxy = new UserDaoProxy(target);
        //        proxy.save();

        IUserDao target = new UserDao();

        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();

        proxy.save();
    }

}
