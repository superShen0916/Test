package test.paytestlog.linfan0524;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Maps;

/**
 * @Description: 得到每个玩家当日最高等级
 * @Author: shenpeng
 * @Date: 2019-05-24
 */
public class Rid_Levle {

    private static final String path1 = "/Volumes/macwin/log/action_log/action_log_2019-04-24.log";

    /**
     * 每个玩家最后的等级
     */
    private static final String path2 = "/Volumes/macwin/log/action_log/rid-level/rid-level-24.log";

    private static Pattern p1 = Pattern.compile("\\{");

    private static Pattern p2 = Pattern.compile("\\}");

    public static void main(String[] args) {

        //   initServer();

        String str = "";

        Map<String, Integer> rid_level = Maps.newHashMap();

        try {

            int line = 0;

            BufferedReader br = new BufferedReader(new FileReader(path1));
            while ((str = br.readLine()) != null) {
                line++;
                if (line % 500000 == 0) {
                    System.out.println(line);
                }

                int index1 = str.indexOf("#") - 6;
                if (index1 < 0) {
                    System.out.println(str);
                    continue;
                }
                String rid = str.substring(index1, index1 + 16);

                int indexDiff;
                if ((indexDiff = str.indexOf("\"diff\":")) != -1) {
                    int indexLevel = indexDiff;
                    int level;
                    while ((indexLevel = str.indexOf("\"level\"", indexLevel) + 1) != 0) {
                        if (checkDiffLevel(str.substring(indexDiff, indexLevel)) == 1) {
                            int index7 = str.indexOf(",", indexLevel);
                            level = Integer.valueOf(str.substring(indexLevel + 7, index7));
                            if (level < rid_level.getOrDefault(rid, 0)) {
                                System.out.println(str);
                            }
                            rid_level.put(rid, level);
                            break;
                        }
                    }

                }

            }

            br.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(path2));
//            bw.write("rid                等级    \n");
            for (Map.Entry<String, Integer> entry : rid_level.entrySet()) {
                bw.write(entry.getKey() + "          " + entry.getValue() + "\n");
                bw.flush();
            }
            bw.close();

            System.out.println("done!!!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("!!!!!! " + str);
        }

    }

    /**
     * 检查层级
     *
     * @param [str, p1, p2]
     * @return boolean
     * @Author: shenpeng
     * @Date: 2019-04-29
     */
    public static int checkDiffLevel(String str) {

        Matcher m1 = p1.matcher(str);
        int count1 = 0;
        while (m1.find()) {
            count1++;
        }

        Matcher m2 = p2.matcher(str);
        int count2 = 0;
        while (m2.find()) {
            count2++;
        }

        return count1 - count2;
    }

}
