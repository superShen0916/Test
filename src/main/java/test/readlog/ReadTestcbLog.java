package test.readlog;

import static java.util.regex.Pattern.compile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 16564
 * 3900
 * 16
 * 
 * @Description: testcb
 * @Author: shenpeng
 * @Date: 2018/12/5
 */
public class ReadTestcbLog {

    public static String filePath1 = "/Users/playcrab/Desktop/log/client_kos_testcb_2018-11-29.log";

    public static String filePath2 = "/Users/playcrab/Desktop/log/test/bufferReader.log";

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(20000);
        long time1 = System.currentTimeMillis();
        String str = null;

        FileReader fileReader = null;
        FileWriter fileWriter = null;
        // FileInputStream in = null;
        try {
            // in = new FileInputStream(filePath1);
            fileReader = new FileReader(filePath1);
            fileWriter = new FileWriter(filePath2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BufferedReader br = null;
        BufferedWriter bw = null;
        int start1 = 0;
        int index1 = 0;
        String rid;
        String devId;
        Map<String, String> rid_devid = Maps.newHashMap();
        Map<String, String> devid_rids = Maps.newHashMap();
        List<String> devList = Lists.newArrayList();
        List<String> errDev = Lists.newArrayList();
        try {
            br = new BufferedReader(fileReader);
            bw = new BufferedWriter(fileWriter);
            while ((str = br.readLine()) != null) {
                start1 = str.indexOf("\"rid\":\"") + 7;
                index1 = str.indexOf("\"device_id\":\"");

                if (start1 != 6 && index1 != -1) {
                    rid = str.substring(start1, start1 + 16);
                    devId = str.substring(index1 + 13, index1 + 28);
                    if (!rid_devid.containsKey(rid)) {
                        rid_devid.put(rid, devId);
                    }
                }
                Thread.sleep(10);
            }
            long time2 = System.currentTimeMillis();
            System.out.println(time2 - time1); //16564

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
            System.out.println(time3 - time2); //3900
            for (Map.Entry<String, String> entry : devid_rids.entrySet()) {
                bw.write(entry.getKey() + " :  " + entry.getValue() + "\n");
                bw.flush();
            }
            long time4 = System.currentTimeMillis();
            System.out.println(time4 - time3); //16
            bw.close();
            br.close();
        } catch (Exception e) {
            //            System.out.println(str.length());
            //            System.out.println(str.indexOf("combat", index1));
            //            System.out.println(index1 + "   " + start1);
            System.out.println(str);
            e.printStackTrace();
        }
    }

    public static boolean level(String str) {
        int index = 0;
        int count1 = 0;
        int count2 = 0;
        while ((index = str.indexOf("{", index)) != -1) {
            index = index + 1;
            count1++;
        }
        while ((index = str.indexOf("}", index)) != -1) {
            index = index + 1;
            count2++;
        }
        return (count1 - count2) == 1;
    }

    public static String getNumbers(String str) {
        Pattern pattern = compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "1";
    }
}
