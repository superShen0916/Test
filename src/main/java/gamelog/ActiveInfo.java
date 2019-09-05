package gamelog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * @Description: 登陆信息
 * @Author: shenpeng
 * @Date: 2019-06-22
 */
public class ActiveInfo {

    private final static String SOURCE_PATH = "/Users/playcrab/Desktop/gamelog/kos_online_active/kos_online_active_20190619.log";

    private final static String RESULT_PATH = "/Users/playcrab/Desktop/gamelog/kos_online_active/active_20190619.log";

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        String str = "";
        Set<String> devIds = Sets.newHashSet();
        try (BufferedReader br = new BufferedReader(new FileReader(SOURCE_PATH));
                BufferedWriter bw = new BufferedWriter(new FileWriter(RESULT_PATH))) {
            while ((str = br.readLine()) != null) {

                int index1 = str.indexOf("device_id") + 12;
                if (index1 == 11) {
                    System.out.println(str);
                    continue;
                }
                int end = str.indexOf("\"", index1);
                String devId = str.substring(index1, end);
                if (!devIds.contains(devId)) {
                    devIds.add(devId);
                    bw.write(devId + "\n");
                    bw.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(str);
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }
}
