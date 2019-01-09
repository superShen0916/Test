package test.readlog;

import java.io.*;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @Description: 刷号的等级分布
 * @Author: shenpeng
 * @Date: 2019/1/2
 */
public class LevelCount {

    public static void main(String[] args) throws IOException {

        String path1 = "/Users/playcrab/Desktop/log/role/uid-level.log";
        String path2 = "/Users/playcrab/Desktop/log/role/levelCount.log";

        BufferedReader br = new BufferedReader(new FileReader(path1));

        String str;
        Map<String, Integer> map = Maps.newHashMap();
        while ((str = br.readLine()) != null) {
            int index = str.indexOf(" ", 27);
            String level = str.substring(27, index);
            if (map.containsKey(level)) {
                map.put(level, map.get(level) + 1);
            } else {
                map.put(level, 1);
            }
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(path2));

        for (int i = 1; i < 120; i++) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (Integer.valueOf(entry.getKey()) == i) {
                    bw.write(entry.getKey() + "  " + entry.getValue() + "\n");
                    bw.flush();
                }
            }
        }

        bw.close();
    }
}
