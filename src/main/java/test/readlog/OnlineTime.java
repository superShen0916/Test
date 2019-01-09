package test.readlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @Description: 29号登陆的账号的每日的在线时间
 * @Author: shenpeng
 * @Date: 2018/12/17
 */
public class OnlineTime {

    static String path1 = "/Volumes/macwin/action日志/08-time.log";

    static String path2 = "/Volumes/macwin/action日志/08-time-rid.log";

    static String path0 = "/Volumes/macwin/action日志/11-29-ge23.log";

    static String path3 = "/Volumes/macwin/action日志/08-time-result2.log";

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(path1));
        BufferedReader reader2 = new BufferedReader(new FileReader(path2));
        BufferedReader reader0 = new BufferedReader(new FileReader(path0));
        String str;
        String uid;
        int length;
        long time;
        int totalTime = 0;
        Set<String> uids = Sets.newHashSet();
        Set<String> uids0 = Sets.newHashSet();
        Map<String, Long> OlTime = Maps.newHashMap();
        Map<String, Boolean> isOnline = Maps.newHashMap();
        long time1 = System.currentTimeMillis() / 1000;
        while ((str = reader0.readLine()) != null) {
            uids0.add(str);
        }
        while ((str = reader2.readLine()) != null) {
            if (!uids0.contains(str)) {
                continue;
            }
            uids.add(str);
            OlTime.put(str, 0L);
            isOnline.put(str, false);
        }
        long time2 = System.currentTimeMillis() / 1000;
        System.out.println("t1 :" + (time2 - time1));
        try {
            while ((str = reader.readLine()) != null) {

                uid = str.substring(0, 5);
                if (!uids.contains(uid)) {
                    continue;
                }
                length = str.length();
                if (!isOnline.get(uid) && length < 40) {
                    time = Integer.valueOf(str.substring(length - 10, length));
                    OlTime.put(uid, time - OlTime.get(uid));
                    isOnline.put(uid, true);
                } else if (isOnline.get(uid) && length > 40) {
                    try {

                        time = Integer.valueOf(str.substring(length - 10, length));
                    } catch (Exception e) {
                        System.out.println(str);
                        continue;
                    }
                    OlTime.put(uid, time - OlTime.get(uid));
                    isOnline.put(uid, false);
                }
            }
            long time3 = System.currentTimeMillis() / 1000;
            System.out.println("t2 :" + (time3 - time2));
            BufferedWriter writer = new BufferedWriter(new FileWriter(path3));
            for (Map.Entry<String, Long> entry : OlTime.entrySet()) {
                if (isOnline.get(entry.getKey())) {
                    entry.setValue(1544284800 - entry.getValue());
                }
                writer.write(entry.getKey() + "  " + entry.getValue() + "\n");
                writer.flush();
                totalTime += entry.getValue();
            }
            System.out.println(totalTime / OlTime.size());
            System.out.println(uids.size());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(str);
        }

    }
}
