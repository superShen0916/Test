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
 *
 * @Description: testcb
 * @Author: shenpeng
 * @Date: 2018/12/5
 */
public class ReadTestcbLog {

    public static String filePath1 = "/Users/playcrab/Desktop/client_kos_testcb_2018-11-29.log";

    public static String filePath2 = "/Users/playcrab/Desktop/testcb.log";

    public static void main(String[] args) {
        String str = null;
        String lastRefreshTime;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(filePath1);
            fileWriter = new FileWriter(filePath2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> ridList = Lists.newArrayList();
        Map<String, Integer> actionMap = Maps.newHashMap();
        BufferedReader br = null;
        BufferedWriter bw = null;
        int start1 = 0;
        int count = 0;
        int index1 = 0;
        int index2 = 0;
        String action;
        String rid;
        String ridAc;
        int i = 0;
        try {
            br = new BufferedReader(fileReader);
            bw = new BufferedWriter(fileWriter);
            while ((str = br.readLine()) != null) {
                i++;
                if (str.contains(",\"rid\":\"")) {
                    index1 = str.indexOf("\"action\":\"") + 10;
                    if (index1 == -1) {
                        System.out.println("action not found \n" + str);
                        continue;
                    }
                    index2 = str.indexOf("\"", index1);
                    action = str.substring(index1, index2);
                    start1 = str.indexOf(",\"rid\":\"") + 8;
                    if (start1 >= str.length() - 3) {
                        System.out.println(i + " rid is null \n" + str);
                        continue;
                    }
                    rid = str.substring(start1, str.length() - 3);
                    ridAc = rid + action;
                    if (ridList.contains(ridAc)) {
                        continue;
                    }
                    ridList.add(ridAc);
                    count = 1;
                    if (actionMap.containsKey(action)) {
                        count = actionMap.get(action) + 1;
                    }
                    actionMap.put(action, count);
                }
            }
            for (Map.Entry<String, Integer> entry : actionMap.entrySet()) {
                bw.write(entry.getKey() + "   " + entry.getValue());
            }

            System.out.println("finished");
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
