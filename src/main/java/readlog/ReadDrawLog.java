package readlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2018/11/19
 */
public class ReadDrawLog {

    public static void main(String[] args) {
        String filePath1 = "/Users/playcrab/Desktop/ac1119.log";
        String filePath2 = "/Users/playcrab/Desktop/1119draw.log";
        String str = null;
        String packId = null;
        String heroId = null;
        String rarity = null;
        String rewardId;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(filePath1);
            fileWriter = new FileWriter(filePath2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int drawAmount = 0;
        int SSRAmount = 0;
        int SR = 0;
        int R = 0;
        int N = 0;
        int O = 0;
        BufferedReader br = null;
        BufferedWriter bw = null;
        int start1 = 0;
        int start2;
        int index1 = 0;
        String midStr;
        int totalAmount = 0;
        int totalSSR = 0;
        //  Map<String, Integer> rewardMap = new HashMap<>();
        try {

            bw = new BufferedWriter(fileWriter);
            for (int id = 10000; id < 10200; id++) {
                SSRAmount = 0;
                drawAmount = 0;
                SR = 0;
                R = 0;
                N = 0;
                O = 0;
                fileReader = new FileReader(filePath1);
                br = new BufferedReader(fileReader);
                while ((str = br.readLine()) != null) {
                    if (!str.contains("audit#" + id + "_101")) {
                        continue;
                    }
                    start1 = str.indexOf("cardPackId");
                    packId = str.substring(start1 + 13, start1 + 17);
                    if (packId.equals("gold")) {
                        continue;
                    }
                    while ((start2 = str.indexOf("\"itemConfigId\":")) != -1) {
                        rewardId = str.substring(start2 + 16, start2 + 25);
                        str = str.substring(start2 + 25);
                        if (rewardId.contains("he")) {
                            //TODO rarity
                            //                            rarity = KOSDataConfigService.getSettingById(HeroBase.class,
                            //                                    rewardId).rarity;
                            switch (rarity) {
                                case "SSR":
                                    SSRAmount++;
                                    totalSSR++;
                                    break;
                                case "SR":
                                    SR++;
                                    break;
                                case "R":
                                    R++;
                                    break;
                                case "N":
                                    N++;
                                    break;
                                default:
                                    System.out.println("异常卡");
                                    break;
                            }
                            rewardId = rewardId + "  " + rarity;
                        } else {
                            O++;
                        }
                        //                    if (rewardMap.containsKey(rewardId)) {
                        //                        rewardMap.put(rewardId, rewardMap.get(rewardId) + 1);
                        //                    } else {
                        //                        rewardMap.put(rewardId, 1);
                        //                    }

                    }

                    switch (packId) {
                        case "box1":
                            drawAmount++;
                            totalAmount++;
                            break;
                        case "box2":
                            drawAmount += 10;
                            totalAmount += 10;
                            break;
                        default:
                            System.out.println(packId);
                            break;
                    }
                }
                if (drawAmount == 0) {
                    System.out.println(id);
                    continue;
                }
                bw.write("玩家id: " + id + " -- ");
                bw.write("抽卡次数：" + drawAmount + " -- ");
                bw.write("SSR：" + SSRAmount + " -- ");
                bw.write("SR：" + SR + " -- ");
                bw.write("R：" + R + " -- ");
                bw.write("N：" + N + " -- ");
                bw.write("其他" + O + "\r\n");

                br.close();

            }
            bw.write("总抽卡次数：" + totalAmount + " -- ");
            bw.write("总SSR：" + totalSSR + "\r\n");
            System.out.println("done");
            bw.close();
        } catch (Exception e) {

            System.out.println(str);
            System.out.println(heroId);
            e.printStackTrace();
        }
    }
}
