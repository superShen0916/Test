package readlog;

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
public class OnlineTime4 {

    static String path1 = "/Volumes/macwin/action日志/";

    static String path3 = "/Volumes/macwin/action日志/count.log";

    static String path4 = "/Volumes/macwin/action日志/onlineTime-";

    static String path0 = "/Users/playcrab/Desktop/log/lte24.txt";

    public static void main(String[] args) throws Exception {

        List<String> dates = Lists.newArrayList();
        dates.add("29-time.log");
        dates.add("30-time.log");
        dates.add("01-time.log");
        dates.add("02-time.log");
        dates.add("03-time.log");
        dates.add("04-time.log");
        dates.add("05-time.log");

        Map<String, Long> times = Maps.newHashMap();
        times.put("29-time.log", 1543507200L);
        times.put("30-time.log", 1543593600L);
        times.put("01-time.log", 1543680000L);
        times.put("02-time.log", 1543766400L);
        times.put("03-time.log", 1543852800L);
        times.put("04-time.log", 1543939200L);
        times.put("05-time.log", 1544025600L);

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

        long t1 = System.currentTimeMillis();

        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());

        CountDownLatch latch = new CountDownLatch(7);

        List<FutureTask> futureTasks = Lists.newArrayList();

        for (

        String date : dates) {
            futureTasks.add(new FutureTask(new Callable() {

                @Override
                public Object call() {
                    Map<String, Long> OlTime = Maps.newHashMap();
                    Map<String, Boolean> isOnline = Maps.newHashMap();
                    Map<String, Integer> map = Maps.newHashMap();
                    String str = "11";
                    try {

                        for (String uid : uids) {
                            OlTime.put(uid, 0L);
                            isOnline.put(uid, false);
                        }
                        BufferedReader bufferedReader = new BufferedReader(
                                new FileReader(path1 + date));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(path4 + date));
                        long time;
                        long totalTime = 0L;
                        while ((str = bufferedReader.readLine()) != null) {
                            if (str.length() < 10) {
                                continue;
                            }
                            String uid = str.substring(0, 5);
                            if (!uids.contains(uid)) {
                                continue;
                            }
                            int length = str.length();
                            if (!isOnline.get(uid) && !str.contains("\t1007\t")) {
                                time = Integer.valueOf(str.substring(length - 10, length));
                                OlTime.put(uid, time - OlTime.get(uid));
                                isOnline.put(uid, true);
                            } else if (isOnline.get(uid) && str.contains("\t1007\t")) {
                                time = Integer.valueOf(str.substring(length - 10, length));
                                OlTime.put(uid, time - OlTime.get(uid));
                                isOnline.put(uid, false);
                            }
                        }

                        for (Map.Entry<String, Long> entry : OlTime.entrySet()) {
                            if (isOnline.get(entry.getKey())) {
                                entry.setValue(times.get(date) - entry.getValue());
                            }
                            writer.write(entry.getKey() + "  " + entry.getValue() + "\n");
                            writer.flush();
                            totalTime += entry.getValue();
                        }
                        System.out.println(date + " :  " + totalTime / OlTime.size());
                        bufferedReader.close();
                        latch.countDown();
                    } catch (Exception e) {
                        System.out.println(str + "   " + date);
                        e.printStackTrace();
                    }
                    return map;
                }

            }));
        }
        for (

        FutureTask futureTask : futureTasks) {
            executorService.submit(futureTask);
        }

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
        latch.await();

        long t3 = System.currentTimeMillis();
        System.out.println(t3 - t2);

        //        Map<String, Integer> count = Maps.newHashMap();
        //        for (String id : uids) {
        //            count.put(id, 0);
        //        }
        //
        //        for (FutureTask task : futureTasks) {
        //            Map<String, Integer> map = (Map<String, Integer>) task.get();
        //
        //            for (Map.Entry<String, Integer> entry : map.entrySet()) {
        //                count.put(entry.getKey(), count.get(entry.getKey()) + entry.getValue());
        //            }
        //        }
        //
        //        BufferedWriter bw = new BufferedWriter(new FileWriter(path3));
        //        for (Map.Entry<String, Integer> entry : count.entrySet()) {
        //            bw.write(entry.getKey() + "  " + entry.getValue() + "\n");
        //            bw.flush();
        //        }
        System.out.println("finish!");
        executorService.shutdown();
    }
}
