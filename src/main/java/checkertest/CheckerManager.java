package checkertest;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @Description: checkerManager
 * @Author: shenpeng
 * @Date: 2019-06-15
 */
public class CheckerManager {

    public static Map<String, Checker> checkers = Maps.newHashMap();

    public static void init() {
        checkers.put("1", new Checker());
    }

}
