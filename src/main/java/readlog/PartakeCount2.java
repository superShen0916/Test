package readlog;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2018/12/26
 */
public class PartakeCount2 {

    static String resultlog = "/Volumes/macwin/action日志/cangjingge/BlockResult_2018-";

    static String blocklog = "/Volumes/macwin/action日志/cangjingge/Block_2018-";

    static String arenalog = "/Volumes/macwin/action日志/cangjingge/ArenaFlow_2018-";

    static String saitamaTasklog = "/Volumes/macwin/action日志/cangjingge/SaitamaTask_2018-";

    static String unionlog = "/Volumes/macwin/action日志/cangjingge/UnionActivity_2018-";

    public static String path0 = "/Users/playcrab/Desktop/log/10.3.8.27_account_info/account_info_2018-";

    public static String path1 = "/Users/playcrab/Desktop/log/10.3.8.47_account_info/account_info_2018-";

    public static String path301 = "/Users/playcrab/Desktop/log/role/role_login_log_2018-11-30.log";

    public static String path011 = "/Users/playcrab/Desktop/log/role/role_login_log_2018-12-01.log";

    public static String path021 = "/Users/playcrab/Desktop/log/role/role_login_log_2018-12-02.log";

    public static void main(String[] args)
            throws InterruptedException, ExecutionException, IOException {
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
        Set<String> uids29 = Sets.newConcurrentHashSet();
        Set<String> uids29vivo = Sets.newConcurrentHashSet();
        Set<String> uids30 = Sets.newConcurrentHashSet();
        Set<String> uids30vivo = Sets.newConcurrentHashSet();
        Set<String> uids01 = Sets.newConcurrentHashSet();
        Set<String> uids01vivo = Sets.newConcurrentHashSet();
        Set<String> uids02 = Sets.newConcurrentHashSet();
        Set<String> uids02vivo = Sets.newConcurrentHashSet();
        Set<String> uids30_01 = Sets.newConcurrentHashSet();
        Set<String> uids30_01vivo = Sets.newConcurrentHashSet();
        Set<String> uids01_02 = Sets.newConcurrentHashSet();
        Set<String> uids01_02vivo = Sets.newConcurrentHashSet();

        BufferedReader br = new BufferedReader(new FileReader(path0 + "11-29.log"));
        String str = null, uid;
        try {
            while ((str = br.readLine()) != null) {
                uids29.add(str.substring(49, 54));
                if (str.contains("VIVO")) {
                    uids29vivo.add(str.substring(49, 54));

                }
            }
            br.close();
            br = new BufferedReader(new FileReader(path1 + "11-29.log"));
            while ((str = br.readLine()) != null) {
                uids29.add(str.substring(49, 54));
                if (str.contains("VIVO")) {
                    uids29vivo.add(str.substring(49, 54));

                }
            }
            br.close();

            /////////////////

            br = new BufferedReader(new FileReader(path301));
            while ((str = br.readLine()) != null) {
                if (str.length() < 54) {
                    continue;
                }
                uid = str.substring(49, 54);
                if (!uids29.contains(uid)) {
                    continue;
                }
                uids30.add(uid);
                if (uids29vivo.contains(uid)) {
                    uids30vivo.add(uid);
                }
            }
            br.close();

            ///////////////

            br = new BufferedReader(new FileReader(path011));
            while ((str = br.readLine()) != null) {
                if (str.length() < 54) {
                    continue;
                }
                uid = str.substring(49, 54);
                if (!uids30.contains(uid)) {
                    continue;
                }
                uids01.add(uid);
                if (uids30vivo.contains(uid)) {
                    uids01vivo.add(uid);
                }
            }
            br.close();

            ///////////////

            br = new BufferedReader(new FileReader(path021));
            while ((str = br.readLine()) != null) {
                if (str.length() < 54) {
                    continue;
                }
                uid = str.substring(49, 54);
                if (!uids01.contains(uid)) {
                    continue;
                }
                uids02.add(uid);
                if (uids01vivo.contains(uid)) {
                    uids02vivo.add(uid);
                }
            }
            br.close();

            //////-----------------

            for (String s : uids30) {
                if (!uids01.contains(s)) {
                    uids30_01.add(s);
                }
            }
            for (String s : uids30vivo) {
                if (!uids01vivo.contains(s)) {
                    uids30_01vivo.add(s);
                }
            }
            for (String s : uids01) {
                if (!uids02.contains(s)) {
                    uids01_02.add(s);
                }
            }
            for (String s : uids01vivo) {
                if (!uids02vivo.contains(s)) {
                    uids01_02vivo.add(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(str);
        }
        Set<String> uids = uids01_02vivo;
        System.out.println(uids29vivo.size());
        System.out.println(uids30vivo.size());
        System.out.println(uids01vivo.size());
        System.out.println(uids02vivo.size());
        System.out.println("人数：" + uids.size() + "\n\n");

        //        Set<String> uids = uids01_02;
        //        System.out.println(uids29.size());
        //        System.out.println(uids30.size());
        //        System.out.println(uids01.size());
        //        System.out.println(uids02.size());
        //        System.out.pr  intln("人数：" + uids.size() + "\n\n");

        ExecutorService executorService = new ThreadPoolExecutor(15, 15, 0L, TimeUnit.SECONDS,
                new SynchronousQueue<>());

        List<FutureTask> futureTasks = Lists.newArrayList();

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
                            return result;
                        }
                    }));
        }

        List<Future> futures = Lists.newArrayList();
        for (FutureTask task : futureTasks) {
            Future future = executorService.submit(task);
            futures.add(future);
        }
        for (FutureTask task : futureTasks) {
            Object object = task.get();
            Map<Integer, Set<String>> result = (Map<Integer, Set<String>>) object;
            System.out.println(result.get(100));
            for (Map.Entry<Integer, Set<String>> entry : result.entrySet()) {
                if (entry.getKey() != 100) {
                    System.out.println(ActivityType.getNameByType(entry.getKey()) + "       "
                            + entry.getValue().size());
                }
            }
            System.out.println("\n\n\n");
        }
    }
}
