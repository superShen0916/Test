package readlog;

import static java.util.regex.Pattern.compile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 统计玩家每天战斗力变化
 * @Author: shenpeng
 * @Date: 2018/10/12
 */
public class ReadCombat {

    public static void main(String[] args) {
        String uid = "10006";
        String str = null;
        String lastRefreshTime;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader("/Users/playcrab/Desktop/a.log");
            fileWriter = new FileWriter("/Users/playcrab/Desktop/combat_" + uid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedReader br = null;
        BufferedWriter bw = null;
        int start1 = 0;
        int start2;
        int index1 = 0;
        String midStr;
        int i = 0;
        try {
            br = new BufferedReader(fileReader);
            bw = new BufferedWriter(fileWriter);
            while ((str = br.readLine()) != null) {
                if (uid.equals(str.substring(0, 5)) && str.indexOf("\"time\":") != -1
                        && str.indexOf("\"diff\"") != -1
                        && str.indexOf("combat", str.indexOf("\"diff\"")) != -1) {
                    index1 = str.indexOf("\"diff\"");
                    start1 = str.indexOf("combat", index1) + 8;
                    midStr = str.substring(index1, start1);

                    if (level(midStr)) {
                        start2 = str.lastIndexOf("\"time\":") + 8;
                        lastRefreshTime = str.substring(start2, start2 + 19);
                        bw.write(lastRefreshTime + "   ");
                        bw.write(getNumbers(str.substring(start1, start1 + 6)) + "\r\n");
                    }
                }
            }
            i++;
            bw.close();
            br.close();
        } catch (Exception e) {
            System.out.println(str.length());
            System.out.println(str.indexOf("combat", index1));
            System.out.println(index1 + "   " + start1);
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
