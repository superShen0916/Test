package test.readlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @Description: 读取7100每个ip有多少设备
 * @Author: shenpeng
 * @Date: 2018/12/24
 */
public class ReadGTN7100_IP {

    private static final String path1 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-11-29.log";

    private static final String path2 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-11-30.log";

    private static final String path3 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-01.log";

    private static final String path4 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-02.log";

    private static final String path5 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-03.log";

    private static final String path6 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-04.log";

    private static final String path7 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-05.log";

    private static final String path8 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-06.log";

    private static final String path9 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-07.log";

    private static final String path10 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-08.log";

    private static final String path11 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-09.log";

    private static final String path12 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-10.log";

    private static final String path13 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-11.log";

    private static final String path14 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-12.log";

    private static final String path15 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-13.log";

    private static final String path0 = "/Users/playcrab/Desktop/log/role/GT-N7100_ip";

    public static void main(String[] args) {
        try {

            Set<String> uids_117 = Sets.newHashSet();

            List<String> files = Lists.newArrayList();
            //            files.add(path1);
            //            files.add(path2);
            files.add(path3);
            //            files.add(path4);
            //            files.add(path5);
            //            files.add(path6);
            //            files.add(path7);
            //            files.add(path8);
            //            files.add(path9);
            //            files.add(path10);
            //            files.add(path11);
            //            files.add(path12);
            //            files.add(path13);
            //            files.add(path14);
            //            files.add(path15);
            String str;
            int count = 0;
            int day = 0;
            Map<String, Integer> ipCount = Maps.newHashMap();
            BufferedWriter bw = null;
            for (String path : files) {
                int num = 0;
                day++;
                BufferedReader br = new BufferedReader(new FileReader(path));
                while ((str = br.readLine()) != null) {
                    if (!str.contains("GT-N7100")) {
                        continue;
                    }
                    num++;
                    count++;
                    int index2 = str.indexOf("\t", 105);
                    if (index2 == -1) {
                        System.out.println(str);
                    }
                    String ip = str.substring(94, index2);
                    if (ipCount.containsKey(ip)) {
                        ipCount.put(ip, ipCount.get(ip) + 1);
                    } else {
                        ipCount.put(ip, 1);
                    }

                    if (ip.contains("117.48.122.34")) {
                        uids_117.add(str.substring(49, 54));
                    }

                }
                br.close();
                bw = new BufferedWriter(new FileWriter(path0 + day));
                for (int i = 5000; i > 0; i--) {
                    for (Map.Entry<String, Integer> entry : ipCount.entrySet()) {
                        if (entry.getValue() == i) {
                            bw.write(entry.getKey() + "     " + entry.getValue() + "\n");
                            bw.flush();
                        }
                    }
                }
                System.out.println(num);
                System.out.println(uids_117.size() + "==");
            }
            uids_117.add("21177");
            MongoLevel.readLevelsByUids(uids_117);
            //            System.out.println(count);
            //            System.out.println(uids_117.size());
        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
