package test.readlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @Description: 同一ip下有哪些设备
 * @Author: shenpeng
 * @Date: 2019-04-24
 */
public class SameIpDevs {

    private static final String path0 = "/Users/playcrab/Desktop/log/role_info_2019-04-23.log";

    private static final String path1 = "/Users/playcrab/Desktop/log/SameIpDevs.log";

    public static void main(String[] args) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path1));

            BufferedReader br = new BufferedReader(new FileReader(path0));

            Map<String, List<String>> ip_devs = Maps.newHashMap();

            String str;
            String ip = "";
            String dev = "";
            while ((str = br.readLine()) != null) {
                String[] array = str.split("\t");
                if (array.length > 4) {
                    ip = array[array.length - 4];
                    dev = array[array.length - 2];

                    if (ip_devs.containsKey(ip)) {
                        ip_devs.get(ip).add(dev);
                    } else {
                        ip_devs.put(ip, Lists.newArrayList());
                        ip_devs.get(ip).add(dev);
                    }
                } else {
                    System.out.println(str);
                }

            }
            int maxSize = 0;

            Map<String, Integer> ip_count = Maps.newHashMap();

            for (Map.Entry<String, List<String>> entry : ip_devs.entrySet()) {
                ip_count.put(entry.getKey(), entry.getValue().size());
                if (entry.getValue().size() > maxSize) {
                    maxSize = entry.getValue().size();
                }
                //                bw.write(entry.getKey() + "        " + entry.getValue().size() + "      "
                //                        + entry.getValue().toString() + "\n");
            }
            int size = 0;
            for (int i = 62; i > 0; i--) {

                for (Map.Entry<String, Integer> entry : ip_count.entrySet()) {
                    if (entry.getValue() == i) {
                        bw.write(entry.getKey() + "     " + ip_devs.get(entry.getKey()).size()
                                + "     " + ip_devs.get(entry.getKey()).toString() + "\n");
                        size += ip_devs.get(entry.getKey()).size();
                        bw.flush();
                    }
                }
            }
            System.out.println(maxSize);
            System.out.println(size);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
