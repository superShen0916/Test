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
 * @Description: 读取rid和机型
 * @Author: shenpeng
 * @Date: 2018/12/18
 */
public class ReadRidDevType {

    public static String path1 = "/Users/playcrab/Desktop/log/testcb/11.29.csv";

    public static String path2 = "/Users/playcrab/Desktop/log/testcb/12.12-login.log";

    public static String path3 = "/Users/playcrab/Desktop/log/testcb/12-login.log";

    public static void main(String[] args) {
        List<String> rids = Lists.newArrayList();
        Set<String> rids2 = Sets.newHashSet();
        int count = 0;
        String str;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path1));
            while ((str = br.readLine()) != null) {
                rids.add(str);
            }
            br.close();

            BufferedReader br2 = new BufferedReader(new FileReader(path2));
            BufferedWriter bw = new BufferedWriter(new FileWriter(path3));
            while ((str = br2.readLine()) != null) {
                rids2.add(str);
            }
            for (String rid : rids) {
                if (rids2.contains(rid)) {
                    count++;
                    bw.write(rid + "\n");
                    bw.flush();
                }
            }
            br2.close();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
