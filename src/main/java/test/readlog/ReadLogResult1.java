package test.readlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @Description: 读取有效数据，将rid与运营给的rid-手机型号表匹配上，输出每个动作每台设备做了多少次
 * @Author: shenpeng
 * @Date: 2018/12/14
 */
public class ReadLogResult1 {

    public static String filePath1 = "/Users/playcrab/Desktop/log/client_kos_testcb_2018-11-29.log";

    public static String filePath2 = "/Users/playcrab/Desktop/log/actionStatistics/acCount.log";

    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        String str = null;
        //        List<String> channels = Lists.newArrayList();
        //        channels.add("Tencent");
        //        channels.add("OPPO");
        //        channels.add("VIVO");
        //        channels.add("playcrab_sdk_source");
        //        channels.add("bilibili");
        //        channels.add("taptap");

        FileReader fileReader = null;
        FileWriter fileWriter = null;
        FileReader fileReader2 = null;
        try {
            fileReader = new FileReader(filePath1);
            fileWriter = new FileWriter(filePath2);
            fileReader2 = new FileReader("/Users/playcrab/Desktop/log/actionStatistics/rid-dev");
        } catch (Exception e) {
            e.printStackTrace();
        }
        BufferedReader br = null;
        BufferedReader br2 = null;
        BufferedWriter bw = null;
        int start1 = 0;
        int index1 = 0;
        int begin, end = 0;
        int count = 0;
        String rid;
        String devId;
        String action;
        String name;
        String resultName;
        String channel;
        Map<String, String> rid_devid = Maps.newHashMap();
        Map<String, Integer> dev_ac = Maps.newHashMap();
        Map<String, String> rid_name = Maps.newHashMap();
        List<String> devList = Lists.newArrayList();
        List<String> errDev = Lists.newArrayList();
        try {
            br = new BufferedReader(fileReader);

            while ((str = br.readLine()) != null) {
                start1 = str.indexOf("\"rid\":\"") + 7;
                index1 = str.indexOf("\"device_id\":\"");

                if (start1 != 6 && index1 != -1) {
                    //rid = str.substring(start1, start1 + 16);
                    rid = str.substring(start1, start1 + 1);
                    devId = str.substring(index1 + 13, index1 + 28);
                    if (!rid_devid.containsKey(rid)) {
                        rid_devid.put(rid, devId);
                    }
                }
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
            rid_devid = null;
            devList = null;
            br.close();
            br2 = new BufferedReader(fileReader2);
            while ((str = br2.readLine()) != null) {
                rid = str.substring(0, 9);
                name = str.substring(10);
                rid_name.put(rid, name);
            }
            br2.close();
            long time3 = System.currentTimeMillis();
            System.out.println(time3 - time2); //3900
            br = new BufferedReader(new FileReader(filePath1));
            while ((str = br.readLine()) != null) {
                count = 0;
                start1 = str.indexOf("\"rid\":\"") + 14;
                index1 = str.indexOf("\"device_id\":\"");
                //                begin = str.indexOf("\"channel\":\"");
                //                end = str.indexOf("\"", begin + 11);
                //                if (begin == -1) {
                //                    continue;
                //                }
                //                channel = str.substring(begin + 11, end);
                //                if (channels.contains(channel)) {
                //                    continue;
                //                }
                if (start1 != 13 && index1 != -1) {
                    devId = str.substring(index1 + 13, index1 + 28);
                    //只统计正常设备的数据
                    if (!errDev.contains(devId)) {
                        rid = str.substring(start1, start1 + 9);
                        begin = str.indexOf("\"action\":\"") + 10;
                        end = str.indexOf("\"", begin);
                        action = str.substring(begin, end);

                        resultName = action + "            " + rid_name.get(rid);

                        if (dev_ac.containsKey(resultName)) {
                            count = dev_ac.get(resultName);
                        }
                        count++;
                        dev_ac.put(resultName, count);
                    }
                }
            }
            long time4 = System.currentTimeMillis();
            System.out.println(time4 - time3); //16
            bw = new BufferedWriter(fileWriter);
            for (Map.Entry<String, Integer> entry : dev_ac.entrySet()) {
                bw.write(entry.getKey() + " :  " + entry.getValue() + "\n");
                bw.flush();
            }
            Thread.sleep(1000000);
            long time5 = System.currentTimeMillis();
            System.out.println(time5 - time4); //16
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
}
