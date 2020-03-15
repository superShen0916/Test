package readlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @Description: 表1 日期 刷新1次玩家人数 刷新2次玩家人数 刷新3次玩家人数 刷新4次 刷新5次 刷新6次 刷新7次及以上
 *
 *
 *
 * @Author: shenpeng
 * @Date: 2019/1/5
 */
public class ShopRefCount {

    private static String path = "/Volumes/macwin/action日志/action_log_2018-";

    private static String path2 = "/Users/playcrab/Desktop/log/shopRefresh";

    public static void main(String[] args) {
        List<String> dates = Lists.newArrayList();
        dates.add("11-29.log");

        long t1 = System.currentTimeMillis();
        String str = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            BufferedWriter bw = new BufferedWriter(new FileWriter(path2));
            while ((str = br.readLine()) != null) {
                if (str.contains("\t8003\t")) {
                    int index = str.indexOf("\"shopId\":");
                    if (index != -1) {
                        String tid = str.substring(index + 24, index + 25);
                        if (Integer.valueOf(tid) > 3) {
                            System.out.println(str);
                        }
                        bw.write(str.substring(0, 5) + "  " + tid + "\n");
                        bw.flush();
                    }
                }
            }
            bw.close();
            br.close();
        } catch (Exception e) {
            System.out.println(str);
            e.printStackTrace();
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

        //统计
        try {
            BufferedReader br = new BufferedReader(new FileReader(path2));
            Map<String, Integer> map1 = Maps.newHashMap();
            Map<Integer, Integer> map2 = Maps.newHashMap();
            map2.put(1, 0);
            map2.put(2, 0);
            map2.put(3, 0);
            map2.put(4, 0);
            map2.put(5, 0);
            map2.put(6, 0);
            while ((str = br.readLine()) != null) {
                String uid = str.substring(0, 5);
                int ac = Integer.valueOf(str.substring(7, 8));
                if (ac <= 3) {
                    if (map1.containsKey(uid)) {
                        map1.put(uid, map1.get(uid) + 1);
                    } else {
                        map1.put(uid, 1);
                    }
                }
                map2.put(ac, map2.get(ac) + 1);
            }

            int c1 = 0, c2 = 0, c3 = 0;
            for (Map.Entry<String, Integer> entry : map1.entrySet()) {
                if (entry.getValue() == 1) {
                    c1++;
                } else if (entry.getValue() == 2) {
                    c2++;
                } else if (entry.getValue() == 3) {
                    c3++;
                }
            }
            System.out.println("领1次人数: " + c1);
            System.out.println("领2次人数: " + c2);
            System.out.println("领3次人数: " + c3);
            System.out.println("中午领人数: " + map2.get(1));
            System.out.println("下午领人数: " + map2.get(2));
            System.out.println("晚上领人数: " + map2.get(3));
            System.out.println("中午补领人数: " + map2.get(4));
            System.out.println("下午补领人数: " + map2.get(5));
            System.out.println("晚上补领人数: " + map2.get(6));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(str);
        }
    }

}
