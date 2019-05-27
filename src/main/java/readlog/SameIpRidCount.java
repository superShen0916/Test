package readlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.TreeSet;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @Description: 同一ip创建了多少账号
 * @Author: shenpeng
 * @Date: 2019-04-11
 */
public class SameIpRidCount {

    public static String filePath = "/Users/playcrab/Desktop/log/testcb/ip.log";

    public static String filePath2 = "/Users/playcrab/Desktop/log/testcb/ip-ridCount.log";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String str;
        int line = 0;

        TreeSet<String> treeSet = Sets.newTreeSet();
        Map<String, Integer> Ip_Count = Maps.newHashMap();
        Map<String, Integer> Ip_Count2 = Maps.newHashMap();
        while ((str = br.readLine()) != null) {
            line++;
            treeSet.add(str);
            if (Ip_Count.containsKey(str)) {
                Ip_Count.put(str, Ip_Count.get(str) + 1);
            } else {
                Ip_Count.put(str, 1);
            }
            if (line % 10000 == 0) {
                System.out.println(line);
            }
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath2));
        bw.write("ip地址         账号数 \n");
        for (String ip : treeSet) {
            if (Ip_Count.get(ip) > 1) {
                Ip_Count2.put(ip, Ip_Count.get(ip));
            }

        }
        System.out.println(Ip_Count2.size() + "  --");
        for (int i = 500; i > 1; i--) {
            for (Map.Entry<String, Integer> entry : Ip_Count2.entrySet()) {
                if (entry.getValue() == i) {
                    bw.write(entry.getKey() + "     " + entry.getValue() + "\n");
                    bw.flush();
                }
            }
        }

        // System.out.println(count + "  " + speIp);
    }
}
