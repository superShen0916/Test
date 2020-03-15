package proxy.staticproxy;

/**
 * @Description: 代理类
 * @Author: shenpeng
 * @Date: 2019-09-05
 */
public class UserDaoProxy implements IUserDao {

    IUserDao target;

    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("before");
        target.save();
        System.out.println("after");
    }
}
