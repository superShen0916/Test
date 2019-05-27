package paytestlog.lostplayer;

/**
 * @Description: 23日的游戏总时长
 * @Author: shenpeng
 * @Date: 2019-04-29
 */
public class OnlineTime {

    private static final String path1 = "/Volumes/macwin/log/action_log_2019-04-23-24/action_log_2019-04-23.log";

    private static final String path2 = "/Volumes/macwin/log/action_log_2019-04-23-24/rid_onlineTime.log";

    private final static String pathw = "/Users/playcrab/Desktop/log/result-04-23.log";

    public static void main(String[] args) throws Exception {
        String s = "ssss";
        int index = 0;
        while ((index = s.indexOf("s", index)) != -1) {
            System.out.println(index);//死循环
        }

        //        String str;
        //        BufferedReader br = new BufferedReader(new FileReader(path1));
        //        while ((str = br.readLine()) != null) {
        //            if (str.contains("\t11001\t")) {
        //                System.out.println(111);
        //            }
        //        }
    }

}
