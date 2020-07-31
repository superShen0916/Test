import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * 
 *               1、将一个4字节的整数的二进制表示中的001替换为011，输出替换后的整数。
 *
 *               2、将一个数组右移几位，比如数组为1 2 3 4，右移一位即为4 1 2 3。
 *
 *               3、输入一个表示十六进制的字符串，转换为十进制的整数输出。
 * 
 * @Author: shenpeng
 * @Date: 2020-05-16
 */
public class Test {

    //c1

    public static void main(String[] args) {

        TestData data = new TestData("male");
        data.setName("lily");

        // Player player = PlayerService.loadPlayerByUId(10033);
        ImmutableList<String> immutableList = ImmutableList.of("a", "b");
        ImmutableList<String> immutableList2 = ImmutableList.<String> builder().add("1").add("2")
                .build();
        immutableList.add("s");
        immutableList2.add("s");
        System.out.println(immutableList.toString());
        System.out.println(immutableList2.toString());

        Boolean a = false;
        Boolean b = new Boolean(false);
        System.out.println(a.equals(b)); // true

        //        String request = Joiner.on("&").join(null, "1");
        //        System.out.println(request);

        Map<String, String> map = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        String s = new String("1");
        String s2 = new String("1");

        map.put(s, s);
        map2.put(s2, s2);

        // System.out.println(map.equals(map2));
        Set<String> set = Sets.newHashSet();
        List<String> list = Lists.newArrayList();
        list.add("1");
        list.add("1");
        list.add("2");
        set.addAll(list);
        System.out.println(set.toString()); //   [1, 2]
        //        for (int i = 0; i < 100; i++) {
        //            try {
        //                System.out.println(i);
        //                Thread.sleep(1000);
        //
        //            } catch (InterruptedException e) {
        //                // TODO Auto-generated catch block
        //                e.printStackTrace();
        //            }
        //        }
        //        System.out.println(0);
        // System.out.println(11 + 'a');//11+97=108

        //        String s = JSONObject.toJSONString(data);
        //        data = JSONObject.parseObject(s, TestData.class);
        //        System.out.println(data.gender + " " + data.name); //male null
    }
}
