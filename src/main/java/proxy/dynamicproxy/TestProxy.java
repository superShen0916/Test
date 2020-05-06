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

        //##### 为什么动态代理的类要实现接口？
        //因为我们最后创建的代理对象首先是继承了proxy类的,我们还需要获取代理对象的方法,而Java只能单继承,这时候就得通过接口来实现了.
        //        UserDao target = new UserDao();
        //必须实现接口，不然会报错 java.lang.ClassCastException: com.sun.proxy.$Proxy0 cannot be cast to proxy.dynamicproxy.UserDao
        //        UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
        //        proxy.save();

    }
}
