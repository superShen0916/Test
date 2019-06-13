package readlog;

import java.io.*;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2019/1/2
 */
public class Ac44058 {

    private static String path = "/Volumes/macwin/action日志/action_log_2018-12-02.log";

    private static String path2 = "/Users/playcrab/Desktop/log/Test_ac";

    private static final String UID = "44058";

    public static void main(String[] args) throws IOException {
        long t1 = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(path));
        BufferedWriter bw = new BufferedWriter(new FileWriter(path2 + UID + ".log"));
        String str;

        Map<String, Integer> map = Maps.newHashMap();

        while ((str = br.readLine()) != null) {
            if (str.length() < 5) {
                continue;
            }
            int index = str.indexOf(" ");
            String uid = str.substring(0, index);
            if (uid.equals(UID)) {
                bw.write(uid + "1111" + "\n");
                if (map.containsKey(uid)) {
                    map.put(uid, map.get(uid) + 1);
                } else {
                    map.put(uid, 1);
                }
                bw.flush();
            }
        }
        bw.close();
        br.close();
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }
}
