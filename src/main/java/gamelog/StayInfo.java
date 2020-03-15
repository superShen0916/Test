package gamelog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @Description: 注册信息
 * @Author: shenpeng
 * @Date: 2019-06-22
 */
public class StayInfo {

    private final static String SOURCE_PATH1 = "/Users/playcrab/Desktop/gamelog/kos_online_client.log";

    private final static String SOURCE_PATH4 = "/Users/playcrab/Desktop/gamelog/kos_online_active/active_20190619.log";

    private final static String SOURCE_PATH2 = "/Users/playcrab/Desktop/gamelog/kos_online_active/active_20190620.log";

    private final static String SOURCE_PATH3 = "/Users/playcrab/Desktop/gamelog/kos_online_active/active_20190621.log";

    private final static String RESULT_PATH = "/Users/playcrab/Desktop/gamelog/stayInfo.log";

    private final static long end18 = 1560873600;

    private final static long end19 = 1560960000;

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        List<String> re18 = Lists.newArrayList();
        List<String> re19 = Lists.newArrayList();
        List<String> re17 = Lists.newArrayList();
        Set<String> regist18 = Sets.newHashSet();
        Set<String> regist19 = Sets.newHashSet();
        Set<String> login20 = Sets.newHashSet();
        Set<String> login21 = Sets.newHashSet();
        Set<String> login19 = Sets.newHashSet();

        String str = "";
        try (BufferedReader br = new BufferedReader(new FileReader(SOURCE_PATH1));
                BufferedReader br2 = new BufferedReader(new FileReader(SOURCE_PATH2));
                BufferedReader br3 = new BufferedReader(new FileReader(SOURCE_PATH3));
                BufferedReader br4 = new BufferedReader(new FileReader(SOURCE_PATH4));
                BufferedWriter bw = new BufferedWriter(new FileWriter(RESULT_PATH))) {
            while ((str = br.readLine()) != null) {
                int index1 = str.indexOf("device_id") + 12;
                if (index1 == 11) {
                    System.out.println(str);
                    continue;
                }
                int end = str.indexOf("\"", index1);
                String devId = str.substring(index1, end);

                int timeIndex = str.indexOf("register_time");
                long time = Integer.valueOf(str.substring(timeIndex + 15, timeIndex + 25));
                if (time < 1560787200) {
                    re17.add(devId);
                } else if (time < end18) {
                    regist18.add(devId);
                    re18.add(devId);
                } else if (time < end19) {
                    regist19.add(devId);
                    re19.add(devId);
                }
            }
            System.out.println(re17.size());
            System.out.println(re18.size());
            System.out.println(re19.size());
            while ((str = br2.readLine()) != null) {
                login20.add(str);
            }
            while ((str = br3.readLine()) != null) {
                login21.add(str);
            }
            while ((str = br4.readLine()) != null) {
                login19.add(str);
            }
            int count19 = 0;
            int count20 = 0;
            int count21 = 0;
            for (String devId : regist19) {
                //                if (login20.contains(devId)) {
                //                    count20++;
                //                }
                if (login21.contains(devId)) {
                    count21++;
                }
            }
            System.out.println("18注册：" + regist18.size());
            System.out.println("19注册：" + regist19.size());
            System.out.println("20登陆:" + login20.size());
            System.out.println("21登陆:" + login21.size());
            System.out.println("19登陆:" + login19.size());
            System.out.println("19号注册在20号登陆：" + count20);
            System.out.println("19号注册在21号登陆：" + count21);
            for (String devId : regist18) {
                if (login19.contains(devId)) {
                    count19++;
                }
                if (login20.contains(devId)) {
                    count20++;
                }
                if (login21.contains(devId)) {
                    count21++;
                }
            }

            System.out.println("18号注册在19号登陆：" + count19);
            System.out.println(0.1 * 10 * count19 / re18.size());
            System.out.println(0.1 * 10 * count20 / re18.size());

            System.out.println("18、19号注册在20号登陆：" + count20);
            System.out.println("18、19号注册在21号登陆：" + count21);

            //            System.out.println("19号注册设备:" + regist19.size() + "  次日留存："
            //                    + (0.1 * 10 * count20 / regist19.size()) + "  三日留存："
            //                    + (0.1 * 10 * count21 / regist19.size()));
            System.out.println("18、19号注册设备:" + (regist19.size() + regist18.size()) + "  次日留存："
                    + (0.1 * 10 * count20 / (regist19.size() + regist18.size())) + "  三日留存："
                    + (0.1 * 10 * count21 / (regist19.size() + regist18.size())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
