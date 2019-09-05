package gamelog;

import java.io.*;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @Description: 开服第一天坏账统计
 * @Author: shenpeng
 * @Date: 2019-06-19
 */
public class BadBill {

    private final static String SOURCE_PATH = "/Users/playcrab/Desktop/gamelog/errorrecharge.txt";

    private final static String RESULT_PATH = "/Users/playcrab/Desktop/gamelog/result2.log";

    public static void main(String[] args) throws IOException {
        long t1 = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(SOURCE_PATH));
        BufferedWriter bw = new BufferedWriter(new FileWriter(RESULT_PATH));
        Map<String, Integer> id_count = Maps.newHashMap();
        String str;

        Map<String, Integer> map = Maps.newHashMap();

        while ((str = br.readLine()) != null) {
            int role = str.indexOf("roleId");
            int start = str.indexOf("_", role) + 1;
            String area = str.substring(start, start + 5);
            id_count.put(area, id_count.getOrDefault(area, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : id_count.entrySet()) {
            bw.write(entry.getKey() + "    " + entry.getValue() + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

}
