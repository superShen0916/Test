package test.paytestlog.guide;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @Description: 引导步骤的通过人数
 * @Author: shenpeng
 * @Date: 2019-04-30
 */
public class GuideSuccessInfo {

    private static final String path1 = "/Users/playcrab/Desktop/log/back_cjg/PlayerGuideRecord_2019-04-23.log";

    private final static String pathw = "/Users/playcrab/Desktop/log/paytest/guide.log";

    public static void main(String[] args) {
        //  KosServer.initServer();

        String str = "";

        try {
            Map<String, Set<String>> step_count = Maps.newHashMap();

            BufferedReader br = new BufferedReader(new FileReader(path1));
            while ((str = br.readLine()) != null) {
                String uid = str.substring(38, 43);
                int index = str.lastIndexOf("|");
                String step = str.substring(index + 1);
                if (step_count.containsKey(step)) {
                    step_count.get(step).add(uid);
                } else {
                    Set<String> set = Sets.newHashSet();
                    set.add(uid);
                    step_count.put(step, set);
                }
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(pathw));

            for (int i = 0; i < 3000; i++) {
                if (step_count.containsKey(String.valueOf(i))) {
                    bw.write(i + "        " + step_count.get(String.valueOf(i)).size() + "\n");
                    bw.flush();
                }
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
