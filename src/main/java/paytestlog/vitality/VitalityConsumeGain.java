package paytestlog.vitality;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 活力消耗获取情况
 * @Author: shenpeng
 * @Date: 2019-05-30
 */
public class VitalityConsumeGain {

    private static final String path1 = "/Users/playcrab/Desktop/vitality/27.log";

    private static final String path2 = "/Users/playcrab/Desktop/vitality/28.log";

    private static final String path3 = "/Users/playcrab/Desktop/vitality/result.log";

    private static Pattern p1 = Pattern.compile("\\{");

    private static Pattern p2 = Pattern.compile("\\}");

    public static void main(String[] args) {

        //   initServer();

        int temp = 0;
        int vitality;

        String str = "";
        try {

            BufferedReader br = new BufferedReader(new FileReader(path1));
            while ((str = br.readLine()) != null) {

                int indexDiff;
                if ((indexDiff = str.indexOf("\"diff\":")) != -1) {
                    int indexVit = indexDiff;
                    int level;
                    while ((indexVit = str.indexOf("\"vitality\":{", indexVit) + 1) != 0) {
                        if (checkDiffLevel(str.substring(indexDiff, indexVit)) == 3) {
                            int index7 = str.indexOf("value", indexVit);
                            if (index7 == -1 || index7 - 60 > indexVit) {
                                continue;
                            }
                            int index8 = str.indexOf("}", index7);
                            vitality = Integer.valueOf(str.substring(index7 + 7, index8));

                            int actionBegin = str.indexOf(",\"action\":\"");
                            int dot = str.indexOf(".", actionBegin);
                            int actionEnd = str.indexOf("\"", dot);
                            String opcode = str.substring(actionBegin + 11, actionEnd);
                            String change = "";
                            if (vitality - temp > 0) {
                                change = "+" + (vitality - temp);
                            } else {
                                change = String.valueOf((vitality - temp));
                            }

                            StringBuffer action = new StringBuffer(opcode);
                            while (action.length() < 50) {
                                action.append(" ");
                            }
                            action.append(change);
                            while (action.length() < 60) {
                                action.append(" ");
                            }
                            action.append(vitality);

                            System.out.println(action);
                            temp = vitality;
                            break;
                        }
                    }

                }
            }

            br.close();

            BufferedReader br2 = new BufferedReader(new FileReader(path2));
            while ((str = br2.readLine()) != null) {

                int indexDiff;
                if ((indexDiff = str.indexOf("\"diff\":")) != -1) {
                    int indexVit = indexDiff;
                    int level;
                    while ((indexVit = str.indexOf("\"vitality\":{", indexVit) + 1) != 0) {
                        if (checkDiffLevel(str.substring(indexDiff, indexVit)) == 3) {
                            int index7 = str.indexOf("value", indexVit);
                            if (index7 == -1 || index7 - 60 > indexVit) {
                                continue;
                            }
                            int index8 = str.indexOf("}", index7);
                            vitality = Integer.valueOf(str.substring(index7 + 7, index8));

                            int actionBegin = str.indexOf(",\"action\":\"");
                            int dot = str.indexOf(".", actionBegin);
                            int actionEnd = str.indexOf("\"", dot);
                            String opcode = str.substring(actionBegin + 11, actionEnd);
                            String change = "";
                            if (vitality - temp > 0) {
                                change = "+" + (vitality - temp);
                            } else {
                                change = String.valueOf((vitality - temp));
                            }

                            StringBuffer action = new StringBuffer(opcode);
                            while (action.length() < 50) {
                                action.append(" ");
                            }
                            action.append(change);
                            while (action.length() < 60) {
                                action.append(" ");
                            }
                            action.append(vitality);

                            System.out.println(action);
                            temp = vitality;
                            break;
                        }
                    }

                }

            }

            br2.close();
            //            BufferedWriter bw = new BufferedWriter(new FileWriter(path2));
            //            //            bw.write("rid                等级    \n");
            //            for (Map.Entry<String, Integer> entry : rid_level.entrySet()) {
            //                bw.write(entry.getKey() + "          " + entry.getValue() + "\n");
            //                bw.flush();
            //            }
            //            bw.close();

            System.out.println("done!!!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("!!!!!! " + str);
        }

    }

    /**
     * 检查层级
     *
     * @param [str, p1, p2]
     * @return boolean
     * @Author: shenpeng
     * @Date: 2019-04-29
     */
    public static int checkDiffLevel(String str) {

        Matcher m1 = p1.matcher(str);
        int count1 = 0;
        while (m1.find()) {
            count1++;
        }

        Matcher m2 = p2.matcher(str);
        int count2 = 0;
        while (m2.find()) {
            count2++;
        }

        return count1 - count2;
    }

}
