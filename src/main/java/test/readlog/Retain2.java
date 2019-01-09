package test.readlog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @Description: VIVO 用户的留存
 * @Author: shenpeng
 * @Date: 2018/12/25
 */
public class Retain2 {

    public static String path0 = "/Users/playcrab/Desktop/10.3.8.27_account_info/account_info_2018-11-29.log";

    public static String path1 = "/Users/playcrab/Desktop/10.3.8.47_account_info/account_info_2018-11-29.log";

    public static String filePath1 = "/Users/playcrab/Desktop/log/testcb/role_login_log_2018-";

    public static String filePath2 = "/Users/playcrab/Desktop/log/testcb/role_login_log_2018-11-30.log";

    public static String path = "/Users/playcrab/Desktop/actions/VIVO.log";

    // public static String filePath2 = "/Users/playcrab/Desktop/log/retain/VIVO-";

    public static void main(String[] args) throws Exception {
        List<String> dates = Lists.newArrayList();
        dates.add("11-29.log");
        dates.add("11-30.log");
        dates.add("12-01.log");
        dates.add("12-02.log");
        dates.add("12-03.log");
        dates.add("12-04.log");
        dates.add("12-05.log");
        dates.add("12-06.log");
        dates.add("12-07.log");
        dates.add("12-08.log");
        dates.add("12-09.log");
        dates.add("12-10.log");
        dates.add("12-11.log");
        dates.add("12-12.log");
        dates.add("12-13.log");

        String str;

        BufferedReader br;
        Set<String> uids = Sets.newHashSet();

        br = new BufferedReader(new FileReader(path0));
        while ((str = br.readLine()) != null) {
            if (!str.contains("VIVO")) {
                continue;
            }
            uids.add(str.substring(49, 54));

        }
        br.close();
        br = new BufferedReader(new FileReader(path1));
        while ((str = br.readLine()) != null) {
            if (!str.contains("VIVO")) {
                continue;
            }
            uids.add(str.substring(49, 54));

        }
        br.close();

        System.out.println(uids.size());
        for (String date : dates) {
            ReadThread readThread = new ReadThread(filePath1, date, uids);
            readThread.call();

        }
    }
}

class ReadThread implements Callable {

    String filename;

    Set<String> uids;

    String date;

    public ReadThread(String filename, String date, Set<String> uids) {
        this.filename = filename;
        this.date = date;
        this.uids = uids;
    }

    @Override
    public Object call() throws Exception {
        Set<String> uids2 = Sets.newHashSet();
        Set<String> devIds2 = Sets.newHashSet();
        String str = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename + date));
            double count = 0;
            while ((str = br.readLine()) != null) {
                int length = str.length();
                if (length < 55) {
                    continue;
                }

                uids2.add(str.substring(49, 54));
            }
            for (String uid : uids) {
                if (uids2.contains(uid)) {
                    count++;
                }
            }
            System.out.println(date.substring(0, 5) + "    " + count + "   " + count / uids.size());
        } catch (Exception e) {
            System.out.println(str);
            e.printStackTrace();
        }

        return uids2;
    }
}
