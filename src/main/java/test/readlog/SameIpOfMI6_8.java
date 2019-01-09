package test.readlog;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @Description: Mi6 和 Mi8 ip重合的rid
 * @Author: shenpeng
 * @Date: 2018/12/25
 */
public class SameIpOfMI6_8 {

    public static String filePath = "/Users/playcrab/Desktop/log/testcb/role_info_2018-11-29.log";

    public static String filePath2 = "/Users/playcrab/Desktop/log/testcb/MI6IpWithMultiRid.log";

    public static String filePath3 = "/Users/playcrab/Desktop/log/testcb/MI8IpWithMultiRid.log";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String str;
        Set<String> m6ips = Sets.newHashSet();
        Set<String> m8ips = Sets.newHashSet();

        while ((str = br.readLine()) != null) {
            if (str.contains("MI 8")) {
                int index2 = str.indexOf("\t", 105);
                if (index2 == -1) {
                    System.out.println(str);
                }
                m8ips.add(str.substring(94, index2));
            } else if (str.contains("MI 6")) {
                int index2 = str.indexOf("\t", 105);
                if (index2 == -1) {
                    System.out.println(str);
                }
                m6ips.add(str.substring(94, index2));
            }
        }
        List<String> ips = Lists.newArrayList();
        Map<String, String> ip_rid6 = Maps.newHashMap();
        Map<String, String> ip_rid8 = Maps.newHashMap();
        Set<String> result6 = Sets.newHashSet();
        Set<String> result8 = Sets.newHashSet();
        for (String ip : m6ips) {
            br.close();
            br = new BufferedReader(new FileReader(filePath));
            while ((str = br.readLine()) != null) {
                if (str.contains(ip)) {
                    if (ip_rid6.containsKey(ip)) {
                        ip_rid6.put(ip, ip_rid6.get(ip) + "  " + str.substring(49, 54));
                        result6.add(ip);
                    } else {

                        ip_rid6.put(ip, str.substring(49, 54));
                    }
                }
            }
        }

        for (String ip : m8ips) {
            br.close();
            br = new BufferedReader(new FileReader(filePath));
            while ((str = br.readLine()) != null) {
                if (str.contains(ip)) {
                    if (ip_rid8.containsKey(ip)) {
                        ip_rid8.put(ip, ip_rid8.get(ip) + "  " + str.substring(49, 54));
                        result8.add(ip);
                    } else {

                        ip_rid8.put(ip, str.substring(49, 54));
                    }
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath2));
        for (String result : result6) {
            bw.write(result + ":    " + ip_rid6.get(result) + "\n");
        }
        bw.close();
        bw = new BufferedWriter(new FileWriter(filePath3));
        for (String result : result8) {
            bw.write(result + ":    " + ip_rid8.get(result) + "\n");
        }
        bw.close();
    }
}
