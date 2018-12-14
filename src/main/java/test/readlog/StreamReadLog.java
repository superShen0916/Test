package test.readlog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @Description: 流式读取大文件，不用一次把文件都读到内存中
 * @Author: shenpeng
 * @Date: 2018/12/10
 */
public class StreamReadLog {

    public static void main(String[] args) throws Exception {
        /**
         * 每一个rid对应的设备id
         */
        Map<String, String> rid_devid = Maps.newHashMap();
        Map<String, String> devid_rids = Maps.newHashMap();
        long time1 = System.currentTimeMillis();
        String line;
        String rid;
        String devId;
        int pos1, pos2, pos3, pos4;
        List<String> devList = Lists.newArrayList();
        List<String> errDev = Lists.newArrayList();
        FileInputStream inputStream = new FileInputStream(
                new File("/Users/playcrab/Desktop/log/client_kos_testcb_2018-11-29.log"));
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            pos1 = line.indexOf("\"rid\":\"") + 7;
            pos3 = line.indexOf("\"device_id\":\"");
            if (pos1 != 6 && pos3 != -1) {
                rid = line.substring(pos1, pos1 + 16);
                if (rid.equals("ion\":\"unlogin\",\"")) {
                    System.out.println(line);
                }
                devId = line.substring(pos3 + 13, pos3 + 28);
                if (!rid_devid.containsKey(rid)) {
                    rid_devid.put(rid, devId);
                }
            }
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        for (String devid : rid_devid.values()) {
            if (devList.contains(devid)) {
                errDev.add(devid);
            } else {
                devList.add(devid);
            }
        }

        for (Map.Entry<String, String> entry : rid_devid.entrySet()) {
            if (errDev.contains(entry.getValue())) {
                if (devid_rids.containsKey(entry.getValue())) {
                    devid_rids.put(entry.getValue(),
                            devid_rids.get(entry.getValue()) + " " + entry.getKey());
                } else {
                    devid_rids.put(entry.getValue(), entry.getKey());
                }
            }
        }
        long time3 = System.currentTimeMillis();
        System.out.println(time3 - time2);
        FileWriter writer = new FileWriter("/Users/playcrab/Desktop/log/test/javaStream.log");
        for (Map.Entry<String, String> entry : devid_rids.entrySet()) {
            writer.write(entry.getKey() + " : " + entry.getValue() + "\n");
            writer.flush();
        }
        long time4 = System.currentTimeMillis();
        System.out.println(time4 - time3);
    }
}
