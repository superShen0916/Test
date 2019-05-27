package test.readlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @Description: 所有登录的玩家 每日进化所最高通关停留情况
 * @Author: shenpeng
 * @Date: 2018/12/22
 */
public class ReadTower {

    static String path1 = "/Volumes/macwin/action日志/11-29-tower.log";

    static String path2 = "/Volumes/macwin/action日志/11-30-tower.log";

    static String path3 = "/Volumes/macwin/action日志/11.29.tower.log";

    static final long time1 = Long.MAX_VALUE;

    static final long time2 = 1543525200000L;

    public static void main(String[] args) {
        String str = null, rid;
        try {

            BufferedReader br = new BufferedReader(new FileReader(path1));
            BufferedReader br2 = new BufferedReader(new FileReader(path2));
            BufferedWriter bw = new BufferedWriter(new FileWriter(path3));

            int index0, index1;
            long time0;
            Map<String, String> prRoom = Maps.newHashMap();//win
            Map<String, String> prlRoom = Maps.newHashMap();//lose
            Map<String, Integer> wRoomCount = Maps.newHashMap();
            Map<String, Integer> lRoomCount = Maps.newHashMap();
            long t0 = System.currentTimeMillis() / 1000;
            while ((str = br.readLine()) != null) {
                index0 = str.indexOf("lastRef");
                if (index0 == -1) {
                    continue;
                }
                time0 = Long.valueOf(str.substring(index0 + 17, index0 + 30));
                //            if (time0 < time1) {
                //                continue;
                //            }
                rid = str.substring(0, 5);
                if (str.contains("Win\":t") || str.contains("skipT")) {
                    index1 = str.indexOf("yBest");
                    if (index1 == -1) {
                        System.out.println(str);
                    }
                    prRoom.put(rid, str.substring(index1 + 7, index1 + 8));
                    prlRoom.remove(rid);
                } else {
                    index1 = str.indexOf("ockI");
                    if (index1 == -1) {
                        System.out.println(str);
                    }
                    prlRoom.put(rid, str.substring(index1 + 8, index1 + 15));
                }
            }
            long t1 = System.currentTimeMillis() / 1000;
            System.out.println(t1 - t0);

            while ((str = br2.readLine()) != null) {
                index0 = str.indexOf("lastRef");
                if (index0 == -1) {
                    continue;
                }
                time0 = Long.valueOf(str.substring(index0 + 17, index0 + 30));
                if (time0 > time2) {
                    break;
                }
                rid = str.substring(0, 5);
                if (str.contains("Win\":t") || str.contains("skipT")) {
                    index1 = str.indexOf("yBest");
                    if (index1 == -1) {
                        System.out.println(str);
                    }
                    prRoom.put(rid, str.substring(index1 + 7, index1 + 8));
                    prlRoom.remove(rid);
                } else {
                    index1 = str.indexOf("ockI");
                    if (index1 == -1) {
                        System.out.println(str);
                    }
                    prlRoom.put(rid, str.substring(index1 + 8, index1 + 15));
                }
            }
            long t2 = System.currentTimeMillis() / 1000;
            System.out.println(t2 - t1);
            for (String s : prRoom.values()) {
                if (wRoomCount.containsKey(s)) {
                    wRoomCount.put(s, wRoomCount.get(s) + 1);
                } else {
                    wRoomCount.put(s, 1);
                }
            }
            for (String s : prlRoom.values()) {
                if (lRoomCount.containsKey(s)) {
                    lRoomCount.put(s, lRoomCount.get(s) + 1);
                } else {
                    lRoomCount.put(s, 1);
                }
            }
            for (Map.Entry<String, Integer> entry : wRoomCount.entrySet()) {
                bw.write(entry.getKey() + "  " + entry.getValue() + "\n");
                bw.flush();
            }
            for (Map.Entry<String, Integer> entry : lRoomCount.entrySet()) {
                bw.write(entry.getKey() + "  " + entry.getValue() + "\n");
                bw.flush();
            }
        } catch (Exception e) {
            System.out.println(str);
            e.printStackTrace();
        }
    }
}

///**
// * @Description:
// * @Author: shenpeng
// * @Date: 2018/12/22
// */
//public class ReadTower {
//
//    //TODO del
//    static String path1 = "/Volumes/macwin/action日志/12-13-tower.log";
//
//    //static String path2 = "/Volumes/macwin/action日志/12-13-tower.log";
//
//    static String path3 = "/Volumes/macwin/action日志/tower/12.13.tower.log";
//
//    static final long time1 = 1544648400000L;
//
//    static final long time2 = 1544648400000L;
//
//    public static void main(String[] args) {
//        initLogger();
//        KOSLogger.outputEmptyLog();
//        String type = MoreObjects.firstNonNull(System.getProperty("server.type"), "GS");
//        System.setProperty("server.type", type);
//        NetConfig.LOGIN_METHOD_OPTYPE = KOSOpCode.PLAYER_HANDLER_LOGIN_1002;
//        NetConfig.DISCONNECT_METHOD_OPTYPE = KOSOpCode.PLAYER_HANDLER_DISCONNECT_1007;
//
//        BaseAgent agent = null;
//        try {
//            KOSServerType serverType = KOSServerType.getServerType(type);
//            RuntimeCfg.initSubClazz(serverType.runtimeClz());
//            agent = serverType.agent();
//            agent.init();
//            //agent.start();
//        } catch (Throwable e) {
//            String msg = String.format("Start server failed, type:%s", type);
//            KOSLogger.ERROR.error(msg, e);
//            if (agent != null) {
//                ((KOSGSAgent) agent).exit();
//            }
//            System.exit(1);
//        }
//
//        String str = null, rid;
//        List<String> list = KOSDataConfigService.getDataConfigIdList(TowerGroup.class);
//
//        try {
//
//            BufferedReader br = new BufferedReader(new FileReader(path1));
//            //BufferedReader br2 = new BufferedReader(new FileReader(path2));
//            BufferedWriter bw = new BufferedWriter(new FileWriter(path3));
//
//            int index0, index1, index2;
//            long time0;
//            Map<String, String> prRoom = Maps.newHashMap();//win
//            Map<String, String> prlRoom = Maps.newHashMap();//lose
//            Map<String, Integer> wRoomCount = Maps.newHashMap();
//            Map<String, Integer> lRoomCount = Maps.newHashMap();
//            long t0 = System.currentTimeMillis() / 1000;
//            while ((str = br.readLine()) != null) {
//                index0 = str.indexOf("lastRef");
//                if (index0 == -1) {
//                    continue;
//                }
//                time0 = Long.valueOf(str.substring(index0 + 17, index0 + 30));
//                if (time0 < time1) {
//                    continue;
//                }
//                rid = str.substring(0, 5);
//                if (str.contains("Win\":t") || str.contains("skipT")) {
//                    index1 = str.indexOf("yBest");
//                    if (index1 == -1) {
//                        System.out.println(str);
//                    }
//                    index2 = str.indexOf(",", index1 + 7);
//                    prRoom.put(rid, str.substring(index1 + 7, index2));
//                    prlRoom.remove(rid);
//                } else {
//                    index1 = str.indexOf("ockI");
//                    if (index1 == -1) {
//                        System.out.println(str);
//                    }
//                    prlRoom.put(rid, str.substring(index1 + 8, index1 + 15));
//                }
//            }
//            long t1 = System.currentTimeMillis() / 1000;
//            System.out.println(t1 - t0);
//
//            //            while ((str = br2.readLine()) != null) {
//            //                index0 = str.indexOf("lastRef");
//            //                if (index0 == -1) {
//            //                    continue;
//            //                }
//            //                time0 = Long.valueOf(str.substring(index0 + 17, index0 + 30));
//            //                if (time0 > time2) {
//            //                    break;
//            //                }
//            //                rid = str.substring(0, 5);
//            //                if (str.contains("Win\":t") || str.contains("skipT")) {
//            //                    index1 = str.indexOf("yBest");
//            //                    if (index1 == -1) {
//            //                        System.out.println(str);
//            //                    }
//            //                    prRoom.put(rid, str.substring(index1 + 7, index1 + 8));
//            //                    if (str.substring(index1 + 7, index1 + 8) .equals("11")){
//            //
//            //                    }
//            //                    prlRoom.remove(rid);
//            //                } else {
//            //                    index1 = str.indexOf("ockI");
//            //                    if (index1 == -1) {
//            //                        System.out.println(str);
//            //                    }
//            //                    prlRoom.put(rid, str.substring(index1 + 8, index1 + 15));
//            //                }
//            //            }
//            long t2 = System.currentTimeMillis() / 1000;
//            System.out.println(t2 - t1);
//            for (String s : prRoom.values()) {
//                if (wRoomCount.containsKey(s)) {
//                    wRoomCount.put(s, wRoomCount.get(s) + 1);
//                } else {
//                    wRoomCount.put(s, 1);
//                }
//            }
//            for (String s : prlRoom.values()) {
//                if (lRoomCount.containsKey(s)) {
//                    lRoomCount.put(s, lRoomCount.get(s) + 1);
//                } else {
//                    lRoomCount.put(s, 1);
//                }
//            }
//
//            for (int i = 1; i <= list.size(); i++) {
//                bw.write(list.get(i - 1) + "        " + removeAndGet(wRoomCount, String.valueOf(i))
//                        + "     " + removeAndGet(lRoomCount, list.get(i - 1)) + "\n");
//                bw.flush();
//            }
//
//            //
//            //            for (Map.Entry<String, Integer> entry : wRoomCount.entrySet()) {
//            // str = list.get(Integer.valueOf(entry.getKey()) - 1);
//            //                    bw.write(str + "     " + entry.getValue()+"     "
//            //                            + removeAndGet(lRoomCount, str)+ "\n");
//            //                    bw.flush();
//            //            }
//            //            for (Map.Entry<String, Integer> entry : lRoomCount.entrySet()) {
//            //                bw.write(entry.getKey() + "     " + 0+"     "
//            //                    + entry.getValue()+ "\n");
//            //                bw.flush();
//            //            }
//
//            //            for (Map.Entry<String, Integer> entry : lRoomCount.entrySet()) {
//            //                bw.write(entry.getKey() + "  " + entry.getValue() + "\n");
//            //                bw.flush();
//            //            }
//            System.out.println("done!!!");
//            System.exit(0);
//        } catch (Exception e) {
//            System.out.println(str);
//            e.printStackTrace();
//        }
//    }
//
//    private static int removeAndGet(Map<String, Integer> map, String key) {
//        if (map.get(key) == null) {
//            return 0;
//        }
//        return map.remove(key);
//    }
//
//    private static void initLogger() {
//        if (StringUtils.isEmpty(System.getProperty("server.home"))) {
//            System.setProperty("server.home", System.getProperty("user.dir"));
//        }
//
//        String configFileStr = System.getProperty("server.home") + "/config/logback.xml";
//        if (System.getProperty("server.config") != null) {
//            configFileStr = System.getProperty("server.config") + "/logback.xml";
//        }
//        System.getProperties().setProperty("logback.configurationFile", configFileStr);
//
//        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
//            LoggerFactory.getLogger(XMLBean.ErrorLogger.class)
//                    .error("Uncaught Exception in thread '" + t.getName() + "'", e);
//            LoggerFactory.getLogger(XMLBean.ErrorLogger.class).error(e.getMessage(), e);
//        });
//    }
//}
