package gamelog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @Description: 购买时数量异常造成数据溢出的情况
 * @Author: shenpeng
 * @Date: 2019-06-22
 */
public class bugNum {

    private final static String SOURCE_PATH = "/Volumes/macwin/log/06-21.log";

    private final static String RESULT_PATH = "/Users/playcrab/Desktop/gamelog/bugNum.log";

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        String str = "";
        try (BufferedReader br = new BufferedReader(new FileReader(SOURCE_PATH));
                BufferedWriter bw = new BufferedWriter(new FileWriter(RESULT_PATH))) {
            int err = 0;

            while ((str = br.readLine()) != null) {
                int client = str.indexOf("\"client\"");
                if (client == -1) {
                    err++;
                    continue;
                }
                //购买数量
                int amount = 0;

                int num = str.indexOf("\"num\"", client);

                if (num != -1) {
                    int start = num + 6;
                    int end1 = str.indexOf(",", start);
                    int end2 = str.indexOf("}", start);
                    amount = Integer.valueOf(str.substring(start, Math.min(end1, end2)));
                } else {
                    num = str.indexOf("\"amount\"", client);
                    if (num != -1) {
                        int start = num + 9;
                        int end1 = str.indexOf(",", start);
                        int end2 = str.indexOf("}", start);
                        amount = Integer.valueOf(str.substring(start, Math.min(end1, end2)));
                    } else {
                        System.out.println(str);
                    }
                }
                if (amount > 10000) {
                    if (!str.contains("\"currencyType\":\"GOLD\"")) {
                        bw.write(str + "\n");
                    }

                }
            }
            System.out.println(err);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(str);
        }

    }

}
