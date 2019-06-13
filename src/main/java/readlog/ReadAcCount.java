package readlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @Description: 每个动作有多少个设备做过，设备动作去重
 * @Author: shenpeng
 * @Date: 2018/12/24
 */
public class ReadAcCount {

    private static final String path1 = "/Users/playcrab/Desktop/log/ac/dev-channel-ac.log";

    private static final String path2 = "/Users/playcrab/Desktop/log/ac/ac-count-NoVivo";

    public static void main(String[] args) {

        String str = null;
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(path2));
            Set<String> channels = Sets.newHashSet();
            //            channels
            // .add("OPPO");
            //            channels.add("tapt");
            //            channels.add("play");

            /**
             * key: ac
             * value: devId set
             */
            Map<String, Set<String>> acs = Maps.newHashMap();
            Map<String, Integer> acc = new HashMap<String, Integer>(16);
            for (String chan : channels) {
                BufferedReader br = new BufferedReader(new FileReader(path1));
                while ((str = br.readLine()) != null) {
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
            }
            //            channels.add("VIVO");
            BufferedReader br = new BufferedReader(new FileReader(path1));
            while ((str = br.readLine()) != null) {
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
