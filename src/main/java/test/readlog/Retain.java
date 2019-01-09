package test.readlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @Description: 1130-1213留存，按手机品牌和系统分
 * @Author: shenpeng
 * @Date: 2018/12/15
 */
public class Retain {

    public static String filePath1 = "/Users/playcrab/Desktop/log/retain/reslog.1544861045979.595074503781.csv";

    public static String filePath2 = "/Users/playcrab/Desktop/log/actionStatistics/acCount.log";

    public static List<String> dates = Lists.newArrayList();

    public static void main(String[] args) throws Exception {
        dates.add("2018/11/30");
        dates.add("2018/12/1");
        dates.add("2018/12/2");
        dates.add("2018/12/3");
        dates.add("2018/12/4");
        dates.add("2018/12/5");
        dates.add("2018/12/6");
        dates.add("2018/12/7");
        dates.add("2018/12/8");
        dates.add("2018/12/9");
        dates.add("2018/12/10");
        dates.add("2018/12/11");
        dates.add("2018/12/12");
        dates.add("2018/12/13");

        BufferedReader br = new BufferedReader(new FileReader(filePath1));
        String str;
        String brand;
        int start1;
        int index1;
        Set<String> brands = Sets.newHashSet();
        while ((str = br.readLine()) != null) {
            start1 = str.indexOf(",", 79);

            if (start1 > 79) {
                brand = str.substring(79, start1);
                brands.add(brand);
            }
        }
        int count = 0;
        int count2 = 0;
        for (String bra : brands) {
            count = 0;
            count2 = 0;
            br = new BufferedReader(new FileReader(filePath1));
            while ((str = br.readLine()) != null) {
                if (str.contains(bra)) {
                    count++;
                }
            }
            for (int i = 1; i <= 14; i++) {
                //                if (){
                //                    count2++;
                //                }
            }
        }
    }

    public void spliteBydate(String filepath) throws Exception {
        String filename;
        BufferedReader reader;
        String str;
        for (String da : dates) {
            filename = "/Users/playcrab/Desktop/log/retain/" + da;
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            reader = new BufferedReader(new FileReader(filepath));
            while ((str = reader.readLine()) != null) {
                if (str.substring(0, 10).equals(da)) {
                    bw.write(str.substring(10, 46) + "\n");
                }
            }
        }
    }
}
