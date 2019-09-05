package proxy.dynamicproxy;

/**
 * @Description: 目标对象
 * @Author: shenpeng
 * @Date: 2019-09-05
 */
public class UserDao implements IUserDao {

    @Override
    public void save() {
        System.out.println("save");
    }
}
