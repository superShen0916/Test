package readlog;

import java.io.*;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @Description:每个玩家每天开始了多少次琦玉任务
 * @Author: shenpeng
 * @Date: 2018/12/25
 */
public class SaitamaTaskDailyCount {

    static String path1 = "/Volumes/macwin/action日志/cangjingge/SaitamaTask_2018-";

    static String path2 = "/Users/playcrab/Desktop/log/saitama/taskCount-";

    public static void main(String[] args) throws IOException {
        List<String> dates = Lists.newArrayList();
        dates.add("11-29.log");
        dates.add("11-30.log");
        dates.add("12-01.log");
        dates.add("12-02.log");
        dates.add("12-03.log");
        dates.add("12-04.log");
        dates.add("12-05.log");
        dates.add("12-06.log");
        dates.add("12-07.log");
        dates.add("12-08.log");
        dates.add("12-09.log");
        dates.add("12-10.log");
        dates.add("12-11.log");
        dates.add("12-12.log");
        dates.add("12-13.log");

        for (String date : dates) {
            TaskThread taskThread = new TaskThread(path1 + date, path2 + date);
            taskThread.run();
        }

    }
}

class TaskThread implements Runnable {

    String path1, path2;

    public TaskThread(String path1, String path2) {
        this.path1 = path1;
        this.path2 = path2;
    }

    @Override
    public void run() {
        String str;
        Map<String, Integer> uid_count = Maps.newHashMap();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path1));
            while ((str = br.readLine()) != null) {
                int length = str.length();
                if (str.substring(length - 1, length).equals("0")) {
                    String uid = str.substring(38, 43);
                    if (uid_count.containsKey(uid)) {
                        uid_count.put(uid, uid_count.get(uid) + 1);
                    } else {
                        uid_count.put(uid, 1);
                    }
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(path2));
            for (int i = 20; i >= 0; i--) {
                for (Map.Entry<String, Integer> entry : uid_count.entrySet()) {
                    if (entry.getValue() == i) {
                        bw.write(entry.getKey() + "     " + entry.getValue() + "\n");
                        bw.flush();
                    }
                }
            }
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
