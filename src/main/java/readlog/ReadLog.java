package readlog;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @Description: 统计玩家每天战斗力变化
 * @Author: shenpeng
 * @Date: 2018/10/12
 */
public class ReadLog {

    //    public static String filePath1 = "/Users/playcrab/Desktop/log/client_kos_testcb_2018-11-29.log";
    //
    //    public static String filePath2 = "/Users/playcrab/Desktop/testcb.log";

    public static void main(String[] args) throws InterruptedException {
        Map<String, Boolean> map = Maps.newLinkedHashMap();
        map.put("1", true);
        map.put("3", false);
        map.put("2", true);
        long n = map.values().stream().filter(a -> a == true).count();
        System.out.println(n);
        if (map.values().stream().filter(a -> a == true).count() > 0) {
            System.out.println(111);
        }

        //        for (int i = 0; i < 1000; i++) {
        //            Thread.sleep(1000);
        //            System.out.println(i);
        //        }
        //        System.out.println(System.getProperties());
        //        long t1 = System.currentTimeMillis();
        //        String str = null;
        //        String lastRefreshTime;
        //        FileReader fileReader = null;
        //        FileWriter fileWriter = null;
        //        try {
        //            fileReader = new FileReader(filePath1);
        //            fileWriter = new FileWriter(filePath2);
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
        //
        //        // BufferedReader br = null;
        //        // BufferedWriter bw = null;
        //        int start1 = 0;
        //        int start2;
        //        int index1 = 0;
        //        String midStr;
        //        int i = 0;
        //        try (BufferedReader br = new BufferedReader(fileReader);
        //                BufferedWriter bw = new BufferedWriter(fileWriter)) {
        //
        //            while ((str = br.readLine()) != null) {
        //                if (str.contains("rid")) {
        //                    Thread.sleep(1000);
        //                    index1 = str.indexOf("\"diff\"");
        //                    start1 = str.indexOf("combat", index1) + 8;
        //                    if (index1 == -1 || start1 == -1) {
        //                        continue;
        //                    }
        //                    midStr = str.substring(index1, start1);
        //
        //                    if (level(midStr)) {
        //                        start2 = str.lastIndexOf("\"time\":") + 8;
        //                        if (start2 == -1) {
        //                            continue;
        //                        }
        //                        lastRefreshTime = str.substring(start2, start2 + 19);
        //                        bw.write(lastRefreshTime + "   ");
        //                        bw.write(getNumbers(str.substring(start1, start1 + 6)) + "\r\n");
        //                    }
        //                    i++;
        //                }
        //
        //            }
        //            System.out.println(i);
        //        } catch (Exception e) {
        //            //            System.out.println(str.length());
        //            //            System.out.println(str.indexOf("combat", index1));
        //            //            System.out.println(index1 + "   " + start1);
        //            System.out.println(str);
        //            e.printStackTrace();
        //        }
        //        long t2 = System.currentTimeMillis();
        //        System.out.println(t2 - t1);
    }

    //    public static boolean level(String str) {
    //        int index = 0;
    //        int count1 = 0;
    //        int count2 = 0;
    //        while ((index = str.indexOf("{", index)) != -1) {
    //            index = index + 1;
    //            count1++;
    //        }
    //        while ((index = str.indexOf("}", index)) != -1) {
    //            index = index + 1;
    //            count2++;
    //        }
    //        return (count1 - count2) == 1;
    //    }
    //
    //    public static String getNumbers(String str) {
    //        Pattern pattern = compile("\\d+");
    //        Matcher matcher = pattern.matcher(str);
    //        while (matcher.find()) {
    //            return matcher.group(0);
    //        }
    //        return "1";
    //    }

}
