package paytestlog.golddraw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @Description: 钻石和招募券获得情况
 * @Author: shenpeng
 * @Date: 2019-06-03
 */
public class DiaDrawGain {

    private static final String DrawId = "it_209102";

    private static final String COLA_STR2 = "it_201002\":{\"amount\":";

    private static final String COLA_STR3 = "it_201003\":{\"amount\":";

    private static final String PATH = "/Users/playcrab/Desktop/action2/action_log_2019-";

    private static final String RESULT = "/Users/playcrab/Desktop/action2/";

    public static void main(String[] args) throws Exception {

        int amount = 0;

        List<String> uids = Lists.newArrayList();
        uids.add("10027");
        uids.add("10003");
        uids.add("10010");

        List<String> dates = Lists.newArrayList();
        dates.add("05-29");
        dates.add("05-30");
        dates.add("05-31");
        dates.add("06-01");
        dates.add("06-02");

        for (String uid : uids) {
            for (String date : dates) {
                BufferedWriter bw = new BufferedWriter(
                        new FileWriter(RESULT + uid + "_" + date + ".log"));
                BufferedReader br = new BufferedReader(new FileReader(PATH + date + ".log"));
                String str;
                while ((str = br.readLine()) != null) {

                    if (!str.substring(0, 5).equals(uid)) {
                        continue;
                    }

                    int index1 = str.indexOf(COLA_STR3) + 21;
                    if (index1 == 20) {
                        continue;
                    }
                    int index2 = str.indexOf('}', index1);
                    if (index2 - index1 > 10) {
                        bw.write("HeroHandler.upgradeGrade           -" + amount + "       0\n");
                        amount = 0;
                        index2 = str.indexOf(',', index1);
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

    }

}
