package readlog;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2018/12/24
 */
public class IOreadLog {

    static String path1 = "/Volumes/macwin/action日志/action_log_2018-11-29.log";

    public static void main(String[] args) {
        //        long time1 = System.currentTimeMillis();
        //        try {
        //            BufferedReader br = new BufferedReader(new FileReader(path1));
        //            int startNum, count = 0;
        //            boolean isNewLine = false;
        //            String str;
        //            while ((str = br.readLine()) != null) {
        //                if (str.contains("10035")) {
        //                    count++;
        //                }
        //            }
        //            System.out.println(count);
        //            long time2 = System.currentTimeMillis();
        //            System.out.println("t: " + (time2 - time1));
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }

        char s = '是';

        System.out.println(String.valueOf(s).getBytes().length);
    }
}
