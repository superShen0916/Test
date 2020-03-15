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
 * @Description: 每种型号设备的登录人数
 * @Author: shenpeng
 * @Date: 2018/12/19
 */
public class DevCount {

    public static String path1 = "/Users/playcrab/Desktop/log/testcb/dev/11.29-dev.log";

    public static String path2 = "/Users/playcrab/Desktop/log/testcb/dev/11.30-dev.log";

    public static String path3 = "/Users/playcrab/Desktop/log/testcb/dev/12.01-dev.log";

    public static String path4 = "/Users/playcrab/Desktop/log/testcb/dev/12.02-dev.log";

    public static String path5 = "/Users/playcrab/Desktop/log/testcb/dev/12.03-dev.log";

    public static String path6 = "/Users/playcrab/Desktop/log/testcb/dev/12.04-dev.log";

    public static String path7 = "/Users/playcrab/Desktop/log/testcb/dev/12.05-dev.log";

    public static String path8 = "/Users/playcrab/Desktop/log/testcb/dev/12.06-dev.log";

    public static String path9 = "/Users/playcrab/Desktop/log/testcb/dev/12.07-dev.log";

    public static String path10 = "/Users/playcrab/Desktop/log/testcb/dev/12.08-dev.log";

    public static String path11 = "/Users/playcrab/Desktop/log/testcb/dev/12.09-dev.log";

    public static String path12 = "/Users/playcrab/Desktop/log/testcb/dev/12.10-dev.log";

    public static String path13 = "/Users/playcrab/Desktop/log/testcb/dev/12.11-dev.log";

    public static String path14 = "/Users/playcrab/Desktop/log/testcb/dev/12.12-dev.log";

    public static String path15 = "/Users/playcrab/Desktop/log/testcb/dev/12.13-dev.log";

    public static String path0 = "/Users/playcrab/Desktop/log/testcb/dev/dev-count.log";

    public static void main(String[] args) throws Exception {
        long time1 = System.currentTimeMillis() / 1000;

        BufferedReader br1 = new BufferedReader(new FileReader(path1));
        BufferedReader br2 = new BufferedReader(new FileReader(path2));
        BufferedReader br3 = new BufferedReader(new FileReader(path3));
        BufferedReader br4 = new BufferedReader(new FileReader(path4));
        BufferedReader br5 = new BufferedReader(new FileReader(path5));
        BufferedReader br6 = new BufferedReader(new FileReader(path6));
        BufferedReader br7 = new BufferedReader(new FileReader(path7));
        BufferedReader br8 = new BufferedReader(new FileReader(path8));
        BufferedReader br9 = new BufferedReader(new FileReader(path9));
        BufferedReader br10 = new BufferedReader(new FileReader(path10));
        BufferedReader br11 = new BufferedReader(new FileReader(path11));
        BufferedReader br12 = new BufferedReader(new FileReader(path12));
        BufferedReader br13 = new BufferedReader(new FileReader(path13));
        BufferedReader br14 = new BufferedReader(new FileReader(path14));
        BufferedReader br15 = new BufferedReader(new FileReader(path15));

        BufferedWriter bw = new BufferedWriter(new FileWriter(path0));
        String str;
        Map<String, Integer> devCount = Maps.newHashMap();
        List<BufferedReader> brs = Lists.newArrayList();
        int count = 0;
        brs.add(br1);
        brs.add(br2);
        brs.add(br3);
        brs.add(br4);
        brs.add(br5);
        brs.add(br6);
        brs.add(br7);
        brs.add(br8);
        brs.add(br9);
        brs.add(br10);
        brs.add(br11);
        brs.add(br12);
        brs.add(br13);
        brs.add(br14);
        brs.add(br15);
        int line = 0;
        for (BufferedReader br : brs) {
            while ((str = br.readLine()) != null) {
                line++;
                if (devCount.containsKey(str)) {
                    devCount.put(str, devCount.get(str) + 1);
                } else {
                    devCount.put(str, 1);
                }
            }
            System.out.println(devCount.get("GT-N7100"));
            br.close();
        }
        long time2 = System.currentTimeMillis() / 1000;
        System.out.println(time2 - time1);

        for (int i = 10000; i > 0; i--) {
            for (Map.Entry<String, Integer> entry : devCount.entrySet()) {
                if (entry.getValue() == i) {
                    bw.write(i + "     ==     " + entry.getKey() + "\n");
                    count += i;
                    bw.flush();
                }
            }
        }
        long time3 = System.currentTimeMillis() / 1000;
        System.out.println(time3 - time2);
        System.out.println("总设备数:" + count);
        System.out.println(line);
    }
}
