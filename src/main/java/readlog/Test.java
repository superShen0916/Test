package readlog;

import java.util.concurrent.*;

/**
 * @Description: 每种型号设备的登录人数
 * @Author: shenpeng
 * @Date: 2018/12/19
 */
public class Test {

    public static String path1 = "/Users/playcrab/Desktop/log/testcb/role_info_2018-11-29.log";

    public static void main(String[] args) throws Exception {

        int a = 2 % 3;
        System.out.println("---" + a);//2

        ExecutorService service = new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MINUTES,
                new LinkedBlockingQueue<Runnable>());

        Future<Integer> result = service.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                Thread.sleep(5000);
                return 1;
            }
        });
        System.out.println(result.isDone());

        System.out.println(2);
        System.out.println(2);
        System.out.println(2);
        System.out.println(result.isDone());
        System.out.println(result.get());
        System.out.println(result.isDone());

        System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024);
        //        new Thread(new Runnable() {
        //
        //            @Override
        //            public void run() {
        //
        //            }
        //        }).start();

        //        List<String> list2 = Lists.newArrayList();
        //        long time1 = System.currentTimeMillis();
        //        for (int i = 0; i < 10; i++) {
        //            String s = String.valueOf(i);
        //            list2.add(s);
        //        }
        //
        //        long time2 = System.currentTimeMillis();
        //        System.out.println(time2 - time1);
        //
        //        List<String> list = Lists.newArrayList();
        //        long time3 = System.currentTimeMillis();
        //        String m;
        //        for (int i = 0; i < 10; i++) {
        //            m = String.valueOf(i);
        //            list.add(m);
        //        }
        //
        //        long time4 = System.currentTimeMillis();
        //        System.out.println(time4 - time3);
        //        System.out.println(list);

        //
        //        BufferedReader br1 = new BufferedReader(new FileReader(path1));
        //
        //        Map<String, Integer> devCount = Maps.newHashMap();
        //        List<BufferedReader> brs = Lists.newArrayList();
        //        int count = 0;
        //        brs.add(br1);
        //        int line = 0;
        //        for (BufferedReader br : brs) {
        //            while (br.readLine() != null) {
        //                line++;
        //
        //            }
        //
        //            br.closeBoss();
        //        }
        //
        //        System.out.println(line);
    }
}
