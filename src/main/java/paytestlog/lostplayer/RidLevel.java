package paytestlog.lostplayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @Description: rid - level
 * @Author: shenpeng
 * @Date: 2019-05-04
 */
public class RidLevel {

    /**
     * rids
     */
    private static final String path1 = "/Volumes/macwin/log/action_log_2019-04-23-24/04-23-rids.log";

    /**
     * rid - level
     */
    private static final String path2 = "/Volumes/macwin/log/action_log_2019-04-23-24/player23/rid-level.log";

    /**
     * 渠道 机型 角色id 最高等级
     */
    private static final String path3 = "/Volumes/macwin/log/action_log_2019-04-23-24/player23/RidLevel.log";

    public static void main(String[] args) {

        String str = "";

        Map<String, String> rid_level = Maps.newHashMap();

        try {
            Set<String> rids = Sets.newHashSet();

            BufferedReader br = new BufferedReader(new FileReader(path1));
            while ((str = br.readLine()) != null) {
                if (!rids.contains(str)) {
                    rids.add(str);
                }
            }
            br.close();

            BufferedReader br2 = new BufferedReader(new FileReader(path2));
            while ((str = br2.readLine()) != null) {

                String rid = str.substring(0, 16);
                String level = str.substring(16);
                rid_level.put(rid, level);
            }
            br2.close();
            System.out.println(rids.size());
            System.out.println(rid_level.size());

            BufferedWriter bw = new BufferedWriter(new FileWriter(path3));

            for (String rid : rids) {
                bw.write(rid + "      " + rid_level.getOrDefault(rid, "1") + "\n");
                bw.flush();
            }

            bw.close();

            System.out.println("done!!!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("!!!!!! " + str);
        }

    }
}
