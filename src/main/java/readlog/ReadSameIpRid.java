package readlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @Description: ip 为 117.48.122.34的rid
 * @Author: shenpeng
 * @Date: 2018/12/24
 */
public class ReadSameIpRid {

    //    private static final String path1 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-11-29.log";
    //
    //    private static final String path2 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-11-30.log";
    //
    //    private static final String path3 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-01.log";
    //
    //    private static final String path4 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-02.log";
    //
    //    private static final String path5 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-03.log";
    //
    //    private static final String path6 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-04.log";

    private static final String path7 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-05.log";

    //    private static final String path8 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-06.log";
    //
    //    private static final String path9 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-07.log";
    //
    private static final String path10 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-08.log";
    //
    //    private static final String path11 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-09.log";
    //
    //    private static final String path12 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-10.log";
    //
    //    private static final String path13 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-11.log";
    //
    //    private static final String path14 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-12.log";
    //
    //    private static final String path15 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-12-13.log";

    private static final String path0 = "/Users/playcrab/Desktop/log/testcb/rids3.log";

    public static void main(String[] args) {
        List<String> files = Lists.newArrayList();
        //        files.add(path1);
        //        files.add(path2);
        //        files.add(path3);
        //        files.add(path4);
        //        files.add(path5);
        //        files.add(path6);
        //files.add(path7);
        //        files.add(path8);
        //        files.add(path9);
        files.add(path10);
        //        files.add(path11);
        //        files.add(path12);
        //        files.add(path13);
        //        files.add(path14);
        //        files.add(path15);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path0));
            for (String file : files) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String str;
                while ((str = br.readLine()) != null) {
                    if (str.contains("117.48.122.34")) {
                        bw.write(str.substring(66, 82) + "\n");
                        bw.flush();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
