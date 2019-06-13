package readlog;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @Description: 测试map最大内存有多大
 * @Author: shenpeng
 * @Date: 2018/12/14
 */
public class MemoryTest {

    //static String path2 = "/Volumes/macwin/action日志/tt";

    public static void main(String[] args) throws Exception {
        Map<String, String> map = Maps.newHashMap();
        try {

            for (int i = 0; i < 200000; i++) {
                map.put("aaaaaaaaaa" + i, "bbbbbbbbbbbb" + i);
            }
        } catch (Exception e) {
            System.out.println(map.size());
            e.printStackTrace();
        }
        System.out.println("------");
        System.out.println(map.get("aaaaaaaaaa12345"));
        System.out.println("-------");
        System.out.println(map.get("aaaaaaaaaa132345"));
        System.out.println("-------");
        System.out.println(map.get("aaaaaaaaaa332345"));
        System.out.println(map.size());
        //        FileReader fileReader2 = new FileReader(
        //                "/Users/playcrab/Desktop/log/actionStatistics/acCount.log");
        //        Set<String> actions = Sets.newHashSet();
        //        BufferedReader br2 = new BufferedReader(fileReader2);
        //        String str;
        //        String ac;
        //        int index = 0;
        //        while ((str = br2.readLine()) != null) {
        //            index = str.indexOf(" ");
        //            ac = str.substring(0, index);
        //            actions.add(ac);
        //        }
        //        br2.close();
        //        System.out.println(actions.size());

        //        String str = "   ";
        //        BufferedReader reader2 = new BufferedReader(new FileReader(path2));
        //        while ((str = reader2.readLine()) != null) {
        //            if (str.contains(" 1007 ")) {
        //                System.out.println(str);
        //            }
        //        }
        //System.out.println(map.entrySet());
    }
}
