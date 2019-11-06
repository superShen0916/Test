package fastjson;

import java.util.Iterator;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description: 合并json对象
 * @Author: shenpeng
 * @Date: 2019-10-22
 */
public class MergeFastJson {

    public static void main(String[] args) {

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
