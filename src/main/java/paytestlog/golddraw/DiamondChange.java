package paytestlog.golddraw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @Description: 钻石变化
 * @Author: shenpeng
 * @Date: 2019-06-03
 */
public class DiamondChange {

    private static final String DrawId = "it_209102";

    private static final String PATH = "/Users/playcrab/Desktop/action2/action_log_2019-";

    private static final String RESULT = "/Users/playcrab/Desktop/action2/";

    public static void main(String[] args) {

        List<String> uids = Lists.newArrayList();
        uids.add("10027");
        uids.add("10003");
        uids.add("10010");
        uids.add("10014");

        List<String> dates = Lists.newArrayList();
        dates.add("05-29");
        dates.add("05-30");
        dates.add("05-31");
        dates.add("06-01");
        dates.add("06-02");
        String str = "";
        try {

            for (String uid : uids) {
                int amount = 0;
                for (String date : dates) {
                    BufferedWriter bw = new BufferedWriter(
                            new FileWriter(RESULT + uid + "_" + date + ".log"));
                    BufferedReader br = new BufferedReader(new FileReader(PATH + date + ".log"));

                    while ((str = br.readLine()) != null) {
                        if (str.length() < 40) {
                            continue;
                        }
                        if (!str.substring(0, 5).equals(uid)) {
                            continue;
                        }

                        int index1 = str.indexOf("diamond\":{\"amount\":") + 19;
                        if (index1 == 18) {
                            continue;
                        }
                        int index2 = str.indexOf('}', index1);
                        if (index2 - index1 > 10) {
                            System.out.println("===" + str);
                        }

                        int newAmount = Integer.valueOf(str.substring(index1, index2));

                        int actionBegin = str.indexOf(",\"action\":\"");
                        int dot = str.indexOf(".", actionBegin);
                        int actionEnd = str.indexOf("\"", dot);
                        String action = str.substring(actionBegin + 11, actionEnd);

                        String change;
                        if (newAmount > amount) {
                            change = "+" + (newAmount - amount);
                        } else {
                            change = String.valueOf(newAmount - amount);
                        }
                        bw.write(action + "           " + change + "       " + newAmount + "\n");
                        amount = newAmount;
                    }
                    br.close();
                    bw.flush();
                    bw.close();
                }
            }
        } catch (Exception e) {
            System.out.println(str);
            e.printStackTrace();

        }
    }
}
