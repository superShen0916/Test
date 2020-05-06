package fastjson;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @Description: 合并json对象
 * @Author: shenpeng
 * @Date: 2019-10-22
 */
public class MergeFastJson {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(15);
        set.add(16);
        set.add(17);
        set.add(32);
        System.out.println(set.toString());

        Map<String, String> hashMap = new HashMap<>();
        Map<String, String> hashTable = new Hashtable<>();
        hashMap.put(null, "s");
        System.out.println(hashMap.size());
        System.out.println(hashMap.get(null));
        hashTable.put(null, "2");
    }

    public JSONObject mergeJson(JSONObject oldJson, JSONObject newJson) {
        Iterator<String> it = oldJson.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            if (newJson.containsKey(key)) {
                Object newObj = newJson.get(key);
                if (newObj instanceof JSONObject) {
                    JSONObject oldObj = oldJson.getJSONObject(key);
                    mergeJson(oldObj, (JSONObject) newObj);
                } else {
                    oldJson.put(key, newObj);
                }
            }
        }
        return oldJson;
    }

}
