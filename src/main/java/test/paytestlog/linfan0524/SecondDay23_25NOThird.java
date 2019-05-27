package test.paytestlog.linfan0524;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * @Description: 次日到达25-25，且第3日未登录 ----543
 * @Author: shenpeng
 * @Date: 2019-05-24
 */
public class SecondDay23_25NOThird {

    /**
     * 24日最后的等级
     */
    private static final String path1 = "/Volumes/macwin/log/action_log/rid-level/rid-level-24.log";

    /**
     * 25日登陆的rid
     */
    private static final String path2 = "/Volumes/macwin/log/action_log/rids/04-25-rids.log";

    public static void main(String[] args) throws Exception {
        Set<String> rids = Sets.newHashSet();
        BufferedReader br = new BufferedReader(new FileReader(path2));
        String str;
        while ((str = br.readLine()) != null) {
            rids.add(str);
        }

        int count = 0;
        BufferedReader br2 = new BufferedReader(new FileReader(path1));
        while ((str = br2.readLine()) != null) {
            String rid = str.substring(0, 16);
            if (rids.contains(rid)) {
                continue;
            }
            int level = Integer.valueOf(str.substring(str.length() - 2).trim());
            if (level > 22 && level < 26) {
                count++;
            }
        }
        System.out.println(count);
    }

}
