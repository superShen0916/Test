package paytestlog.expitem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @Description: 经验可乐的获取和消耗情况
 * @Author: shenpeng
 * @Date: 2019-06-01
 */
public class ExpItemGainConsume {

    private static final String COLA_STR1 = "it_201001\":{\"amount\":";

    private static final String COLA_STR2 = "it_201002\":{\"amount\":";

    private static final String COLA_STR3 = "it_201003\":{\"amount\":";

    private static final String PATH1 = "/Users/playcrab/Desktop/action/05-29.log";

    private static final String PATH2 = "/Users/playcrab/Desktop/action/05-30.log";

    private static final String PATH3 = "/Users/playcrab/Desktop/action/05-31.log";

    private static final String PATH4 = "/Users/playcrab/Desktop/action/it_201001.log";

    private static final String PATH5 = "/Users/playcrab/Desktop/action/it_201002.log";

    private static final String PATH6 = "/Users/playcrab/Desktop/action/it_201003.log";

    public static void main(String[] args) throws Exception {

        int amount = 0;

        List<String> paths = Lists.newArrayList();
        paths.add(PATH1);
        paths.add(PATH2);
        paths.add(PATH3);
        BufferedWriter bw = new BufferedWriter(new FileWriter(PATH6));
        for (String path : paths) {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String str;
            while ((str = br.readLine()) != null) {

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

        }
        bw.close();

    }

}
