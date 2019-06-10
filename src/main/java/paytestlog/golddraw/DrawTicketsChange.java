package paytestlog.golddraw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @Description: 抽卡券变化
 * @Author: shenpeng
 * @Date: 2019-06-03
 */
public class DrawTicketsChange {

    private static final String DrawId = "it_209102";

    private static final String PATH = "/Users/playcrab/Desktop/action2/action_log_2019-";

    private static final String RESULT = "/Users/playcrab/Desktop/action2/drawTickets/";

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
                int account = 2;
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

                        if (str.contains("\t7001\t")) {
                            int packIdStart = str.indexOf("cardPackId") + 13;
                            String packId = str.substring(packIdStart, packIdStart + 4);
                            if (packId.equals("box1")) {
                                amount = amount - 1;
                                bw.write("DrawHandler.draw         -1      " + amount + "\n");
                            } else if (packId.equals("box2")) {
                                if (account > 0) {
                                    account--;
                                    amount = amount - 8;
                                    bw.write("DrawHandler.draw         -8      " + amount + "\n");
                                } else {
                                    amount = amount - 10;
                                    bw.write("DrawHandler.draw         -10     " + amount + "\n");
                                }
                            }
                        }

                        int index1 = str.indexOf("\"it_209102\":{\"amount\":") + 22;
                        if (index1 == 21) {
                            continue;
                        }
                        int index2 = str.indexOf(',', index1);
                        if (str.substring(index1, index2).contains("}")) {
                            index2 = str.indexOf("}", index1);
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
