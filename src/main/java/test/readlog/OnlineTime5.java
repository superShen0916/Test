package test.readlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * @Description: 第7天登陆的3464人1到7天的平均每天在线时长
 * @Author: shenpeng
 * @Date: 2018/12/29
 */
public class OnlineTime5 {

    static String path1 = "/Volumes/macwin/action日志/action_log_2018-";

    static String path3 = "/Volumes/macwin/action日志/onlineTime/java-";

    static String path0 = "/Users/playcrab/Desktop/log/lte24.txt";

    public static void main(String[] args) throws Exception {

        List<String> dates = Lists.newArrayList();
        dates.add("11-29.log");

        long t1 = System.currentTimeMillis();
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new DefaultThreadFactory("my-pool-name"));

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

                        BufferedWriter bw = new BufferedWriter(new FileWriter(path3));
                        String str;

                        while ((str = bufferedReader.readLine()) != null) {
                            if (str.length() < 10) {
                                continue;
                            }
                            bw.write(str.substring(0, 5));
                        }
                        bufferedReader.close();
                        System.out.println(date + "     done!");
                        latch.countDown();
                        long t2 = System.currentTimeMillis();
                        System.out.println(t2 - t1);
                        System.out.println("==================");
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
        System.out.println();
        Map<String, Integer> count = Maps.newHashMap();

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
