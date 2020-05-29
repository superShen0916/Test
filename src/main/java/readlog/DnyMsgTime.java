package readlog;

import java.util.*;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-05-29
 */
public class DnyMsgTime {

    private static String str = "{\"averageTime\":289,\"maxTime\":832,\"msgNum\":35,\"opCode\":32001}\n"
            + "{\"averageTime\":162,\"maxTime\":338,\"msgNum\":153,\"opCode\":15002}\n"
            + "{\"averageTime\":131,\"maxTime\":387,\"msgNum\":120,\"opCode\":44003}\n"
            + "{\"averageTime\":102,\"maxTime\":727,\"msgNum\":2029,\"opCode\":12003}\n"
            + "{\"averageTime\":93,\"maxTime\":149,\"msgNum\":3,\"opCode\":40004}\n"
            + "{\"averageTime\":93,\"maxTime\":350,\"msgNum\":370,\"opCode\":29005}\n"
            + "{\"averageTime\":90,\"maxTime\":331,\"msgNum\":1439,\"opCode\":1002}\n"
            + "{\"averageTime\":89,\"maxTime\":354,\"msgNum\":964,\"opCode\":40002}\n"
            + "{\"averageTime\":78,\"maxTime\":80,\"msgNum\":2,\"opCode\":26027}\n"
            + "{\"averageTime\":72,\"maxTime\":342,\"msgNum\":189,\"opCode\":21003}\n"
            + "{\"averageTime\":69,\"maxTime\":332,\"msgNum\":296,\"opCode\":16006}\n"
            + "{\"averageTime\":67,\"maxTime\":257,\"msgNum\":341,\"opCode\":41001}\n"
            + "{\"averageTime\":57,\"maxTime\":141,\"msgNum\":315,\"opCode\":20001032}\n"
            + "{\"averageTime\":47,\"maxTime\":47,\"msgNum\":1,\"opCode\":17003}\n"
            + "{\"averageTime\":46,\"maxTime\":396,\"msgNum\":1413,\"opCode\":16001}\n"
            + "{\"averageTime\":44,\"maxTime\":283,\"msgNum\":378,\"opCode\":26018}\n"
            + "{\"averageTime\":41,\"maxTime\":398,\"msgNum\":13135,\"opCode\":13001}\n"
            + "{\"averageTime\":37,\"maxTime\":64,\"msgNum\":11,\"opCode\":54018}\n"
            + "{\"averageTime\":36,\"maxTime\":233,\"msgNum\":2009,\"opCode\":12002}\n"
            + "{\"averageTime\":36,\"maxTime\":63,\"msgNum\":22,\"opCode\":26014}\n"
            + "{\"averageTime\":35,\"maxTime\":197,\"msgNum\":447,\"opCode\":11006}\n"
            + "{\"averageTime\":34,\"maxTime\":466,\"msgNum\":313,\"opCode\":16003}\n"
            + "{\"averageTime\":34,\"maxTime\":300,\"msgNum\":3331,\"opCode\":12001}\n"
            + "{\"averageTime\":33,\"maxTime\":357,\"msgNum\":1294,\"opCode\":20003}\n"
            + "{\"averageTime\":27,\"maxTime\":228,\"msgNum\":56,\"opCode\":1015}\n"
            + "{\"averageTime\":19,\"maxTime\":306,\"msgNum\":734,\"opCode\":16008}\n"
            + "{\"averageTime\":18,\"maxTime\":112,\"msgNum\":255,\"opCode\":16002}\n"
            + "{\"averageTime\":15,\"maxTime\":218,\"msgNum\":200,\"opCode\":23001}\n"
            + "{\"averageTime\":15,\"maxTime\":79,\"msgNum\":896,\"opCode\":1014}\n"
            + "{\"averageTime\":15,\"maxTime\":40,\"msgNum\":4,\"opCode\":43002}\n"
            + "{\"averageTime\":14,\"maxTime\":220,\"msgNum\":489,\"opCode\":16005}\n"
            + "{\"averageTime\":14,\"maxTime\":207,\"msgNum\":19,\"opCode\":6005}\n"
            + "{\"averageTime\":13,\"maxTime\":37,\"msgNum\":3,\"opCode\":1021}\n"
            + "{\"averageTime\":12,\"maxTime\":216,\"msgNum\":1039,\"opCode\":21001}\n"
            + "{\"averageTime\":12,\"maxTime\":25,\"msgNum\":3,\"opCode\":6006}\n"
            + "{\"averageTime\":11,\"maxTime\":20,\"msgNum\":32,\"opCode\":4001}\n"
            + "{\"averageTime\":11,\"maxTime\":214,\"msgNum\":52,\"opCode\":23004}\n"
            + "{\"averageTime\":10,\"maxTime\":13,\"msgNum\":8,\"opCode\":40003}\n"
            + "{\"averageTime\":10,\"maxTime\":28,\"msgNum\":37,\"opCode\":16007}\n"
            + "{\"averageTime\":9,\"maxTime\":9,\"msgNum\":1,\"opCode\":17002}\n"
            + "{\"averageTime\":9,\"maxTime\":55,\"msgNum\":154,\"opCode\":16019}\n"
            + "{\"averageTime\":9,\"maxTime\":21,\"msgNum\":43,\"opCode\":12009}\n"
            + "{\"averageTime\":9,\"maxTime\":37,\"msgNum\":10,\"opCode\":54020}\n"
            + "{\"averageTime\":9,\"maxTime\":217,\"msgNum\":683,\"opCode\":26019}\n"
            + "{\"averageTime\":8,\"maxTime\":8,\"msgNum\":2,\"opCode\":17001}\n"
            + "{\"averageTime\":8,\"maxTime\":57,\"msgNum\":3089,\"opCode\":7001}\n"
            + "{\"averageTime\":8,\"maxTime\":8,\"msgNum\":1,\"opCode\":3015}\n"
            + "{\"averageTime\":7,\"maxTime\":211,\"msgNum\":413,\"opCode\":16013}\n"
            + "{\"averageTime\":7,\"maxTime\":18,\"msgNum\":29,\"opCode\":16027}\n"
            + "{\"averageTime\":7,\"maxTime\":35,\"msgNum\":2911,\"opCode\":5001}\n"
            + "{\"averageTime\":7,\"maxTime\":14,\"msgNum\":171,\"opCode\":26021}\n"
            + "{\"averageTime\":6,\"maxTime\":7,\"msgNum\":2,\"opCode\":17013}\n"
            + "{\"averageTime\":6,\"maxTime\":6,\"msgNum\":1,\"opCode\":17021}\n"
            + "{\"averageTime\":6,\"maxTime\":51,\"msgNum\":636,\"opCode\":16009}\n"
            + "{\"averageTime\":6,\"maxTime\":18,\"msgNum\":12,\"opCode\":12010}\n"
            + "{\"averageTime\":6,\"maxTime\":101,\"msgNum\":116,\"opCode\":54022}\n"
            + "{\"averageTime\":6,\"maxTime\":19,\"msgNum\":14,\"opCode\":26002}\n"
            + "{\"averageTime\":6,\"maxTime\":16,\"msgNum\":121,\"opCode\":44002}\n"
            + "{\"averageTime\":6,\"maxTime\":23,\"msgNum\":6,\"opCode\":43001}\n"
            + "{\"averageTime\":5,\"maxTime\":23,\"msgNum\":1416,\"opCode\":1031}\n"
            + "{\"averageTime\":5,\"maxTime\":14,\"msgNum\":210,\"opCode\":43022}\n"
            + "{\"averageTime\":5,\"maxTime\":128,\"msgNum\":648,\"opCode\":42004}\n"
            + "{\"averageTime\":5,\"maxTime\":9,\"msgNum\":180,\"opCode\":14015}\n"
            + "{\"averageTime\":5,\"maxTime\":7,\"msgNum\":6,\"opCode\":54004}\n"
            + "{\"averageTime\":5,\"maxTime\":12,\"msgNum\":14,\"opCode\":54016}\n"
            + "{\"averageTime\":5,\"maxTime\":30,\"msgNum\":543,\"opCode\":32006}\n"
            + "{\"averageTime\":5,\"maxTime\":25,\"msgNum\":41,\"opCode\":54025}\n"
            + "{\"averageTime\":5,\"maxTime\":9,\"msgNum\":31,\"opCode\":26016}\n"
            + "{\"averageTime\":5,\"maxTime\":74,\"msgNum\":145,\"opCode\":3004}\n"
            + "{\"averageTime\":4,\"maxTime\":6,\"msgNum\":3,\"opCode\":43021}\n"
            + "{\"averageTime\":4,\"maxTime\":14,\"msgNum\":59,\"opCode\":43025}\n"
            + "{\"averageTime\":4,\"maxTime\":27,\"msgNum\":1252,\"opCode\":16004}\n"
            + "{\"averageTime\":4,\"maxTime\":12,\"msgNum\":262,\"opCode\":37001}\n"
            + "{\"averageTime\":4,\"maxTime\":9,\"msgNum\":99,\"opCode\":16011}\n"
            + "{\"averageTime\":4,\"maxTime\":7,\"msgNum\":46,\"opCode\":16015}\n"
            + "{\"averageTime\":4,\"maxTime\":6,\"msgNum\":2,\"opCode\":16016}\n"
            + "{\"averageTime\":4,\"maxTime\":207,\"msgNum\":404,\"opCode\":14005}\n"
            + "{\"averageTime\":4,\"maxTime\":7,\"msgNum\":65,\"opCode\":14006}\n"
            + "{\"averageTime\":4,\"maxTime\":9,\"msgNum\":324,\"opCode\":14017}\n"
            + "{\"averageTime\":4,\"maxTime\":8,\"msgNum\":10,\"opCode\":54013}\n"
            + "{\"averageTime\":4,\"maxTime\":7,\"msgNum\":6,\"opCode\":32038}\n"
            + "{\"averageTime\":4,\"maxTime\":83,\"msgNum\":1441,\"opCode\":8009}\n"
            + "{\"averageTime\":4,\"maxTime\":214,\"msgNum\":1637,\"opCode\":6001}\n"
            + "{\"averageTime\":4,\"maxTime\":16,\"msgNum\":899,\"opCode\":6002}\n"
            + "{\"averageTime\":4,\"maxTime\":247,\"msgNum\":1667,\"opCode\":26001}\n"
            + "{\"averageTime\":4,\"maxTime\":11,\"msgNum\":81,\"opCode\":26007}\n"
            + "{\"averageTime\":4,\"maxTime\":10,\"msgNum\":46,\"opCode\":26008}\n"
            + "{\"averageTime\":4,\"maxTime\":6,\"msgNum\":4,\"opCode\":46001}\n"
            + "{\"averageTime\":4,\"maxTime\":17,\"msgNum\":15,\"opCode\":3005}\n"
            + "{\"averageTime\":4,\"maxTime\":6,\"msgNum\":10,\"opCode\":24001}\n"
            + "{\"averageTime\":4,\"maxTime\":10,\"msgNum\":784,\"opCode\":3012}\n"
            + "{\"averageTime\":4,\"maxTime\":52,\"msgNum\":1842,\"opCode\":1007}\n"
            + "{\"averageTime\":4,\"maxTime\":262,\"msgNum\":7481,\"opCode\":1016}\n"
            + "{\"averageTime\":4,\"maxTime\":9,\"msgNum\":3,\"opCode\":43005}\n"
            + "{\"averageTime\":3,\"maxTime\":6,\"msgNum\":4,\"opCode\":43008}\n"
            + "{\"averageTime\":3,\"maxTime\":9,\"msgNum\":8,\"opCode\":43017}\n"
            + "{\"averageTime\":3,\"maxTime\":4,\"msgNum\":2,\"opCode\":43024}\n"
            + "{\"averageTime\":3,\"maxTime\":13,\"msgNum\":1785,\"opCode\":42003}\n"
            + "{\"averageTime\":3,\"maxTime\":5,\"msgNum\":59,\"opCode\":43030}\n"
            + "{\"averageTime\":3,\"maxTime\":4,\"msgNum\":59,\"opCode\":43031}\n";

    public static void main(String[] args) {
        String[] arr = str.split("\n");

        Map<Integer, String> map = new LinkedHashMap<>();
        int i = 0;
        for (String s : arr) {
            i++;
            map.put(Integer.valueOf(s.substring(15, s.indexOf(","))) * Integer.valueOf(
                    s.substring(s.indexOf("msgNum") + 8, s.indexOf(",", s.indexOf("msgNum") + 8)))
                    * 1000 + i, s.substring(s.lastIndexOf(":") + 1, s.length() - 1));
        }
        List<Map.Entry<Integer, String>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<Integer, String>>() {

            @Override
            public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                return o1.getKey() - o2.getKey();
            }
        });
        for (Map.Entry<Integer, String> entry : list) {
            System.out.println(entry.getKey() / 1000 + "    " + entry.getValue());
        }
    }

}
