package paytestlog.linfan0524;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @Description: 次日玩家等级分布，只统计前两天都登录过的
 * @Author: shenpeng
 * @Date: 2019-05-24
 */
public class SecondDayLevelCount {

    /**
     * 23日登陆的rid
     */
    private static final String path1 = "/Volumes/macwin/log/action_log/rids/04-23-rids.log";

    /**
     * 24日登陆的rid
     */
    private static final String path2 = "/Volumes/macwin/log/action_log/rids/04-24-rids.log";

    /**
     * 24日最后的等级
     */
    private static final String path3 = "/Volumes/macwin/log/action_log/rid-level/rid-level-24.log";

    public static void main(String[] args) throws Exception {
        String str;
        Set<String> rids23 = Sets.newHashSet();
        Set<String> rids24 = Sets.newHashSet();
        Map<Integer, Integer> level_count = Maps.newHashMap();

        BufferedReader br = new BufferedReader(new FileReader(path1));
        while ((str = br.readLine()) != null) {
            rids23.add(str.trim());
        }
        br.close();
        BufferedReader br2 = new BufferedReader(new FileReader(path2));
        while ((str = br2.readLine()) != null) {
            rids24.add(str.trim());
        }
        br2.close();
        Iterator<String> it = rids24.iterator();
        while (it.hasNext()) {
            String rid = it.next();
            if (!rids23.contains(rid)) {
                it.remove();
            }
        }

        BufferedReader br3 = new BufferedReader(new FileReader(path3));
        while ((str = br3.readLine()) != null) {
            String rid = str.substring(0, 16);
            if (rids24.contains(rid)) {
                int level = Integer.valueOf(str.substring(str.length() - 2).trim());
                level_count.put(level, level_count.getOrDefault(level, 0) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : level_count.entrySet()) {
            System.out.println(entry.getKey() + "   " + entry.getValue());
        }
    }

}
