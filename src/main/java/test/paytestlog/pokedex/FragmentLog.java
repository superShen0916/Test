package test.paytestlog.pokedex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @Description: 统计每天玩家身上最后剩多少碎片
 * @Author: shenpeng
 * @Date: 2019-05-08
 */
public class FragmentLog {

    private final static String ADD_LOG = "/Users/playcrab/Desktop/log/paytest/reslog.1557283696128.642.csv";

    private final static String REDUCE_LOG = "/Users/playcrab/Desktop/log/paytest/reslog.1557283363175.162.csv";

    private final static String WRITE_DIR = "/Users/playcrab/Desktop/log/paytest/fragment/";

    public static void main(String[] args) {

        String str = "";
        Map<String, FragmentPlayer> playerMap = Maps.newHashMap();

        List<String> dates = new ArrayList<>();
        dates.add("\"2019-04-23");
        dates.add("\"2019-04-24");
        dates.add("\"2019-04-25");
        dates.add("\"2019-04-26");
        dates.add("\"2019-04-27");
        dates.add("\"2019-04-28");
        dates.add("\"2019-04-29");
        dates.add("\"2019-04-30");
        dates.add("\"2019-05-01");
        dates.add("\"2019-05-02");
        dates.add("\"2019-05-03");
        dates.add("\"2019-05-04");
        dates.add("\"2019-05-05");
        dates.add("\"2019-05-06");
        dates.add("\"2019-05-07");

        try {
            for (String date : dates) {
                BufferedReader addBr = new BufferedReader(new FileReader(ADD_LOG));
                BufferedReader reduceBr = new BufferedReader(new FileReader(REDUCE_LOG));

                while ((str = addBr.readLine()) != null) {
                    if (!str.startsWith(date)) {
                        continue;
                    }
                    int nameEnd = str.indexOf("\"", 23);
                    if (nameEnd == -1) {
                        System.out.println(str);
                        continue;
                    }
                    String name = str.substring(23, nameEnd);
                    int fragBegin = str.indexOf("it_22");
                    String fragId = str.substring(fragBegin, fragBegin + 9);
                    int countBegin = str.indexOf("]", fragBegin + 10) + 4;
                    int countEnd = str.indexOf("\"", countBegin);
                    int count = Integer.valueOf(str.substring(countBegin, countEnd));
                    FragmentPlayer player = playerMap.getOrDefault(name, new FragmentPlayer(name));
                    player.addFragment(fragId, count);
                    playerMap.put(name, player);
                }
                addBr.close();
                while ((str = reduceBr.readLine()) != null) {
                    if (!str.startsWith(date)) {
                        continue;
                    }
                    int nameEnd = str.indexOf("\"", 23);
                    if (nameEnd == -1) {
                        System.out.println(str);
                        continue;
                    }
                    String name = str.substring(23, nameEnd);
                    int fragBegin = str.indexOf("it_22");
                    String fragId = str.substring(fragBegin, fragBegin + 9);
                    int countBegin = str.indexOf("]", fragBegin + 10) + 4;
                    int countEnd = str.indexOf("\"", countBegin);
                    int count = Integer.valueOf(str.substring(countBegin, countEnd));
                    FragmentPlayer player = playerMap.getOrDefault(name, new FragmentPlayer(name));
                    player.reduceFragment(fragId, count);
                    playerMap.put(name, player);
                }
                reduceBr.close();

                BufferedWriter bw = new BufferedWriter(
                        new FileWriter(WRITE_DIR + date.substring(1) + ".log"));
                for (FragmentPlayer value : playerMap.values()) {
                    bw.write(value.toString() + "\n");
                    bw.flush();
                }
                bw.close();
                System.out.println(date + "   done!!!");
            }

        } catch (Exception e) {
            System.out.println(str);
            e.printStackTrace();
        }
    }

}
