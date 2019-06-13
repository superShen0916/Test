package readlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @Description: 去除了刷号玩家之后的action次数
 * @Author: shenpeng
 * @Date: 2018/12/25
 */
public class NormalUidActionCount {

    private static final String path1 = "/Users/playcrab/Desktop/log/ac/dev-channel-ac.log";

    private static final String path2 = "/Users/playcrab/Desktop/log/ac/ac-count-NormalUser.log";

    private static final String path3 = "/Users/playcrab/Desktop/log/abnormalAccount.csv";

    private static final String path4 = "/Users/playcrab/Desktop/log/client_kos_testcb_2018-11-29.log";

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis() / 1000;
        String str = null;
        List<String> abnormalUids = Lists.newArrayList();
        Set<String> abnormalDevids = Sets.newHashSet();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path3));
            while ((str = br.readLine()) != null) {
                abnormalUids.add(str.substring(1, 6));
            }
            br.close();
            for (String uid : abnormalUids) {
                br = new BufferedReader(new FileReader(path4));
                while ((str = br.readLine()) != null) {
                    if (str.contains(uid)) {
                        abnormalDevids.add(str.substring(14, 29));
                        break;
                    }
                }
                br.close();
            }
            long t2 = System.currentTimeMillis() / 1000;
            System.out.println("获取异常设备列表:" + (t2 - t1));
            BufferedWriter bw = new BufferedWriter(new FileWriter(path2));
            Set<String> channels = Sets.newHashSet();
            channels.add("OPPO");
            channels.add("tapt");
            channels.add("play");

            /**
             * key: ac
             * value: devId set
             */
            Map<String, Set<String>> acs = new HashedMap();
            Map<String, Integer> acc = new HashedMap();
            for (String chan : channels) {
                br = new BufferedReader(new FileReader(path1));
                while ((str = br.readLine()) != null) {
                    if (abnormalDevids.contains(str.substring(0, 15))) {
                        continue;
                    }
                    String channel = str.substring(18, 22);
                    if (!channel.equals(chan)) {
                        continue;
                    }
                    String devid = str.substring(0, 15);
                    int index = str.indexOf("-");
                    String ac = str.substring(index + 2);

                    if (acs.containsKey(ac)) {
                        acs.get(ac).add(devid);
                    } else {
                        Set<String> devs = Sets.newHashSet();
                        devs.add(devid);
                        acs.put(ac, devs);
                    }

                }

                for (Map.Entry<String, Set<String>> entry : acs.entrySet()) {
                    if (acc.containsKey(entry.getKey())) {
                        acc.put(entry.getKey(), acc.get(entry.getKey()) + entry.getValue().size());
                    } else {
                        acc.put(entry.getKey(), entry.getValue().size());
                    }
                }
                acs = new HashedMap();
                br.close();
            }
            long t3 = System.currentTimeMillis() / 1000;
            System.out.println("统计oppo tapt play 用户:" + (t3 - t2));
            //            channels.add("VIVO");
            br = new BufferedReader(new FileReader(path1));
            while ((str = br.readLine()) != null) {
                if (abnormalDevids.contains(str.substring(0, 15))) {
                    continue;
                }
                String channel = str.substring(18, 22);
                if (channels.contains(channel)) {
                    continue;
                }
                String devid = str.substring(0, 15);
                int index = str.indexOf("-");
                String ac = str.substring(index + 2);
                if (acs.containsKey(ac)) {
                    acs.get(ac).add(devid);
                } else {
                    Set<String> devs = Sets.newHashSet();
                    devs.add(devid);
                    acs.put(ac, devs);
                }
            }

            for (Map.Entry<String, Set<String>> entry : acs.entrySet()) {
                if (acc.containsKey(entry.getKey())) {
                    acc.put(entry.getKey(), acc.get(entry.getKey()) + entry.getValue().size());
                } else {
                    acc.put(entry.getKey(), entry.getValue().size());
                }
            }
            acs = null;
            long t4 = System.currentTimeMillis() / 1000;
            System.out.println("统计其他用户:" + (t4 - t3));
            for (Map.Entry<String, Integer> entry : acc.entrySet()) {

                bw.write(entry.getKey() + "               " + entry.getValue() + "\n");
                bw.flush();
            }
        } catch (Exception e) {
            System.out.println(str);
            e.printStackTrace();
        }
    }
}
