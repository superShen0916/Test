package readlog;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @Description: 各种玩法参与人数
 * @Author: shenpeng
 * @Date: 2018/12/26
 */
public class PartakeCount {

    static String resultlog = "/Volumes/macwin/action日志/cangjingge/BlockResult_2018-";

    static String blocklog = "/Volumes/macwin/action日志/cangjingge/Block_2018-";

    static String arenalog = "/Volumes/macwin/action日志/cangjingge/ArenaFlow_2018-";

    static String saitamaTasklog = "/Volumes/macwin/action日志/cangjingge/SaitamaTask_2018-";

    static String unionlog = "/Volumes/macwin/action日志/cangjingge/UnionActivity_2018-";

    public static String path0 = "/Users/playcrab/Desktop/log/10.3.8.27_account_info/account_info_2018-";

    public static String path1 = "/Users/playcrab/Desktop/log/10.3.8.47_account_info/account_info_2018-";

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long time1 = System.currentTimeMillis();

        CountDownLatch c = new CountDownLatch(15);
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

        /**
         * 要统计的vivo渠道用户的uid
         */
        Set<String> uids = Sets.newConcurrentHashSet();
        String str;
        ExecutorService executorService = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());

        List<FutureTask> futureTasks = Lists.newArrayList();
        for (String date : dates) {
            futureTasks.add(new FutureTask<Integer>(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(path0 + date));
                        String str;
                        while ((str = br.readLine()) != null) {
                            if (str.contains("VIVO")) {
                                uids.add(str.substring(49, 54));
                            }
                        }
                        br.close();
                        br = new BufferedReader(new FileReader(path1 + date));

                        while ((str = br.readLine()) != null) {
                            if (str.contains("VIVO")) {
                                uids.add(str.substring(49, 54));
                            }
                        }
                        br.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return 1;
                }
            }));
        }

        for (FutureTask task : futureTasks) {
            executorService.submit(task);
        }

        for (FutureTask task : futureTasks) {
            task.get();
        }
        //到这里已经得到了需要统计的uids
        futureTasks.clear();
        for (String date : dates) {
            futureTasks.add(new FutureTask<Map<Integer, Set<String>>>(
                    new Callable<Map<Integer, Set<String>>>() {

                        Map<Integer, Set<String>> result = Maps.newHashMap();

                        BufferedReader br;

                        String str;

                        @Override
                        public Map<Integer, Set<String>> call() throws Exception {

                            for (int i = 0; i < 13; i++) {
                                result.put(i, new HashSet<>());
                            }
                            result.put(100, new HashSet<>());
                            result.get(100).add(date);

                            br = new BufferedReader(new FileReader(resultlog + date));
                            while ((str = br.readLine()) != null) {
                                String uid = str.substring(38, 43);
                                if (!uids.contains(uid)) {
                                    continue;
                                }
                                int type = Integer.valueOf(str.substring(50, 51));
                                if (result.containsKey(type)) {
                                    result.get(type).add(uid);
                                }
                            }
                            br.close();
                            br = new BufferedReader(new FileReader(blocklog + date));
                            while ((str = br.readLine()) != null) {
                                String uid = str.substring(38, 43);
                                if (!uids.contains(uid)) {
                                    continue;
                                }
                                int type = Integer.valueOf(str.substring(50, 51));
                                if (result.containsKey(type)) {
                                    result.get(type).add(uid);
                                }
                            }
                            br.close();
                            //竞技场 type:8
                            br = new BufferedReader(new FileReader(arenalog + date));
                            while ((str = br.readLine()) != null) {
                                String uid = str.substring(38, 43);
                                if (!uids.contains(uid)) {
                                    continue;
                                }
                                result.get(8).add(uid);
                            }
                            br.close();
                            //琦玉任务 type:9
                            br = new BufferedReader(new FileReader(saitamaTasklog + date));
                            while ((str = br.readLine()) != null) {
                                String uid = str.substring(38, 43);
                                if (!uids.contains(uid)) {
                                    continue;
                                }
                                String start = str.substring(57, 58);
                                if ("0".equals(start)) {
                                    result.get(9).add(uid);
                                }
                            }
                            br.close();
                            br = new BufferedReader(new FileReader(unionlog + date));
                            while ((str = br.readLine()) != null) {
                                String uid = str.substring(38, 43);
                                if (!uids.contains(uid)) {
                                    continue;
                                }
                                int type = Integer.valueOf(str.substring(50, 51));
                                if (type == 3) {
                                    continue;
                                }
                                result.get(type + 10).add(uid);

                            }
                            br.close();
                            c.countDown();
                            return result;
                        }
                    }));
        }

        List<Future> futures = Lists.newArrayList();
        for (FutureTask task : futureTasks) {
            Future future = executorService.submit(task);
            futures.add(future);
        }
        System.out.println("1");
        c.await();
        System.out.println("2");
        for (FutureTask task : futureTasks) {
            Object object = task.get();
            //  Map<Integer, Set<String>> result = (Map<Integer, Set<String>>) object;
            //            System.out.println(result.get(100));
            //            for (Map.Entry<Integer, Set<String>> entry : result.entrySet()) {
            //                if (entry.getKey() != 100) {
            //                    System.out.println(ActivityType.getNameByType(entry.getKey()) + "       "
            //                            + entry.getValue().size());
            //                }
            //            }
            //            System.out.println("\n\n\n");
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        executorService.shutdown();
    }
}
