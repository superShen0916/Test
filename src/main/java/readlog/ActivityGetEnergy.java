package readlog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @Description: 咱们活动领体力那个 帮忙统计下 领体力的活动 每天活跃的玩家 每天的领取次数吧 日期 领3次人数 领2次人数
 *               领1次人数 中午领取人数 下午领取人数 晚上领取人数 中午补领人数 下午补领人数 晚上补领人数
 *               =====
 *
 *
 *
 * @Author: shenpeng
 * @Date: 2019/1/5
 */
public class ActivityGetEnergy {

    private static String path = "/Volumes/macwin/action日志/action_log_2018-12-02.log";

    private static String path2 = "/Users/playcrab/Desktop/log/12-07-uid_energy.log";

    public static void main(String[] args) {

        long t1 = System.currentTimeMillis();
        String str = null;
        //        try {
        //            BufferedReader br = new BufferedReader(new FileReader(path));
        //            BufferedWriter bw = new BufferedWriter(new FileWriter(path2));
        //            while ((str = br.readLine()) != null) {
        //                if (str.contains("32012")) {
        //                    int index = str.indexOf("\"aid\":\"110001\",\"tid");
        //                    if (index != -1) {
        //                        String tid = str.substring(index + 24, index + 25);
        //                        if (Integer.valueOf(tid) > 3) {
        //                            System.out.println(str);
        //                        }
        //                        bw.write(str.substring(0, 5) + "  " + tid + "\n");
        //                        bw.flush();
        //                    }
        //                } else if (str.contains("32013")) {
        //                    int index = str.indexOf("\"aid\":\"110001\",\"tid");
        //                    if (index != -1) {
        //                        int tid = Integer.valueOf(str.substring(index + 24, index + 25));
        //                        if (tid > 3) {
        //                            System.out.println(str);
        //                        }
        //                        bw.write(str.substring(0, 5) + "  " + (tid + 3) + "\n");
        //                        bw.flush();
        //                    }
        //                }
        //            }
        //            bw.close();
        //            br.close();
        //        } catch (Exception e) {
        //            System.out.println(str);
        //            e.printStackTrace();
        //        }
        //        long t2 = System.currentTimeMillis();
        //        System.out.println(t2 - t1);

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
