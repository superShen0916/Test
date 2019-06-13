package exception;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @Description: 测试能不能捕获Error ----> 可以
 * @Author: shenpeng
 * @Date: 2019-06-05
 */
public class CacheError {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();
        try {

            list.add(0, 1);
            Test(list);
        } catch (StackOverflowError e) {
            //  e.printStackTrace();
            System.out.println(list.get(0));
        }
    }

    public static void Test(List<Integer> list) {
        list.set(0, list.get(0) + 1);
        Test(list);
    }

}
