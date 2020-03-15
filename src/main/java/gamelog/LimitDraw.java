package gamelog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 统计0703 限时招募抽到 菠萝斯 次数
 *
 * @Description:
 * @Author: shenpeng
 * @Date: 2019-07-04
 */
public class LimitDraw {

    public final static String SOURCE = "/Users/playcrab/Desktop/gamelog/action_log_2019-07-03_32034.log";

    public final static String RESULT = "/Users/playcrab/Desktop/gamelog/32034_result.log";

    public static void main(String[] args) throws Exception {
        long t1 = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(SOURCE));
        BufferedWriter bw = new BufferedWriter(new FileWriter(RESULT));

        String target = "\"code\":\"he_100055\"";

        Map<String, Integer> id_count = Maps.newHashMap();
        String str;

        while ((str = br.readLine()) != null) {
            if (str.contains(target)) {
                String rid = str.substring(0, 6);
                id_count.put(rid, id_count.getOrDefault(rid, 0) + 1);
            }
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
