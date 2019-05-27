package staticobject;

import java.util.List;

/**
 * @Description: user
 * @Author: shenpeng
 * @Date: 2018/11/13
 */
public class TestUser {

    public static void main(String[] args) {
        List<String> list = Newtest.list;
        list.add("1");
        System.out.println(Newtest.list);
    }
}
