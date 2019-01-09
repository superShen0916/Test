package test.readlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @Description: 第7天登陆的3464人1到7天的平均每天在线时长
 * @Author: shenpeng
 * @Date: 2018/12/29
 */
public class OnlineTime3 {

    static String path1 = "/Volumes/macwin/action日志/action_log_2018-";

    static String path3 = "/Volumes/macwin/action日志/count.log";

    static String path0 = "/Users/playcrab/Desktop/log/lte24.txt";

    public static void main(String[] args) throws Exception {

        List<String> dates = Lists.newArrayList();
        dates.add("11-29.log");
        dates.add("11-30.log");
        dates.add("12-01.log");
        dates.add("12-02.log");
        dates.add("12-03.log");
        dates.add("12-04.log");
        dates.add("12-05.log");

        String str = "-";
        String uid;
        Set<String> uids = Sets.newHashSet();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path0));
            while ((str = br.readLine()) != null) {
                uids.add(str.substring(1, 6));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("--- " + str);
        }
        System.out.println("size:" + uids.size());
        long t1 = System.currentTimeMillis();
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());

        CountDownLatch latch = new CountDownLatch(7);

        List<FutureTask> futureTasks = Lists.newArrayList();

        for (String date : dates) {
            futureTasks.add(new FutureTask(new Callable() {

                @Override
                public Object call() {
                    Map<String, Integer> map = Maps.newHashMap();
                    try {
                        BufferedReader bufferedReader = null;

                        bufferedReader = new BufferedReader(new FileReader(path1 + date));

                        String str;

                        while ((str = bufferedReader.readLine()) != null) {
                            if (str.length() < 10) {
                                continue;
                            }
                            String uid = str.substring(0, 5);
                            if (!uids.contains(uid)) {
                                continue;
                            }
                            if (str.contains("32006")) {
                                int index = str.indexOf("aid\"");
                                if ("40001".equals(str.substring(index + 6, index + 11))) {

                                    if (map.containsKey(uid)) {
                                        map.put(uid, map.get(uid) + 1);
                                    } else {
                                        map.put(uid, 1);
                                    }
                                }
                            }
                        }
                        bufferedReader.close();
                        System.out.println(date + "     done!");
                        latch.countDown();
                        long t2 = System.currentTimeMillis();
                        System.out.println(t2 - t1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return map;
                }

            }));
        }
        for (FutureTask futureTask : futureTasks) {
            executorService.submit(futureTask);
        }

        latch.await();

        Map<String, Integer> count = Maps.newHashMap();
        for (String id : uids) {
            count.put(id, 0);
        }

        for (FutureTask task : futureTasks) {
            Map<String, Integer> map = (Map<String, Integer>) task.get();

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                count.put(entry.getKey(), count.get(entry.getKey()) + entry.getValue());
            }
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(path3));
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            bw.write(entry.getKey() + "  " + entry.getValue() + "\n");
            bw.flush();
        }
        System.out.println("finish!");
    }
}
