package test.lambda;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @Description:map遍历删除
 * @Author: shenpeng
 * @Date: 2019-02-26
 */
public class MapRemove {

    public static void main(String[] args) {
        Map<String, String> map = Maps.newHashMap();
        map.put("1", null);
        map.put("2", "2");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                map.remove(entry.getKey());
                System.out.println(entry);
            }
        }
    }

}
