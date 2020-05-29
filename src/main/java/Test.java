import com.alibaba.fastjson.JSONObject;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-05-16
 */
public class Test {

    public static void main(String[] args) {

        TestData data = new TestData("male");
        data.setName("lily");

        String s = JSONObject.toJSONString(data);
        data = JSONObject.parseObject(s, TestData.class);
        System.out.println(data.gender + " " + data.name); //male null
    }
}
