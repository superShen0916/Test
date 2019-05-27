package test.paytestlog.lostplayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @Description: 渠道 机型 角色id 最高等级 所有23日登录的玩家 截止时间 24号0点。
 * @Author: shenpeng
 * @Date: 2019-05-04
 */
public class ChaDevLevelRid2 {

    /**
     * 每个玩家最后的等级
     */
    private static final String path1 = "/Volumes/macwin/log/action_log_2019-04-23-24/action_log_2019-04-23.log";

    /**
     * 登录日志
     */
    private static final String path2 = "/Volumes/macwin/log/action_log_2019-04-23-24/0423login.log";

    /**
     * 渠道 机型 角色id 最高等级
     */
    private static final String path3 = "/Volumes/macwin/log/action_log_2019-04-23-24/player23/chaDevRidLevel.log";

    private static Pattern p1 = Pattern.compile("\\{");

    private static Pattern p2 = Pattern.compile("\\}");

    public static void main(String[] args) {

        String str = "";

        Map<String, String> rid_level = Maps.newHashMap();

        Map<String, String> rid_info = Maps.newHashMap();

        try {
            int line = 0;

            Set<String> rids = Sets.newHashSet();
            Set<String> rids2 = Sets.newHashSet();
            BufferedReader br2 = new BufferedReader(new FileReader(path2));
            while ((str = br2.readLine()) != null) {

                int ridBegin = str.indexOf("#") - 6;
                if (ridBegin < 0) {
                    System.out.println(str);
                    continue;
                }
                String rid = str.substring(ridBegin, ridBegin + 16);
                rids.add(rid);
            }
            System.out.println(rids.size());
            br2.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(path3));

            BufferedReader br = new BufferedReader(new FileReader(path1));
            while ((str = br.readLine()) != null) {
                line++;
                if (line % 500000 == 0) {
                    System.out.println(line);
                }

                int ridBegin = str.indexOf("#") - 6;
                if (ridBegin < 0) {
                    System.out.println(str);
                    continue;
                }
                String rid = str.substring(ridBegin, ridBegin + 16);
                if (!rids.contains(rid) && !rids2.contains(rid)) {
                    bw.write(str + "\n");
                    bw.flush();
                    rids2.add(rid);
                }
            }
            br.close();
            bw.close();

            System.out.println("done!!!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("!!!!!! " + str);
        }

    }
}
