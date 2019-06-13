package paytestlog.lostplayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.tool.XMLBean;
import org.slf4j.LoggerFactory;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.playcrab.common.agent.BaseAgent;
import com.playcrab.common.config.RuntimeCfg;
import com.playcrab.kos.agents.KOSGSAgent;
import com.playcrab.kos.common.config.KOSServerType;
import com.playcrab.kos.common.dataconfig.NormalBlock;
import com.playcrab.kos.common.dataconfig.TaskConfig;
import com.playcrab.kos.common.logger.KOSLogger;
import com.playcrab.kos.common.utils.KOSDataConfigService;
import com.playcrab.kos.gs.enums.KOSOpCode;
import com.playcrab.kos.gs.enums.TaskType;
import com.playcrab.net.mina.typical.NetConfig;

/**
 * @Description: 筛选用户范围 23日注册且24日没登录的玩家
 *               筛选等级范围 14-15级
 *
 *               数据需求
 *               流失前的最后首通 普通关卡ID、精英关卡ID
 *               23日的游戏总时长
 *               1-13级升级所用的时间，需要去掉离线时间
 *               当日完成的日常任务ID
 * @Author: shenpeng
 * @Date: 2019-04-29
 */
public class PlayerLostInfo {

    private static final String path1 = "/Volumes/macwin/log/action_log_2019-04-23-24/action_log_2019-04-23.log";

    private static final String path2 = "/Volumes/macwin/log/action_log_2019-04-23-24/lostplayer/result.log";

    private static final String pathRids = "/Volumes/macwin/log/action_log_2019-04-23-24/04-24-rids.log";

    private final static String pathw = "/Users/playcrab/Desktop/log/result-04-23.log";

    private final static String pathw2 = "/Users/playcrab/Desktop/log/result2.log";

    private static final long FINTIME = 1556035200;
    //  private static Pattern p0 = Pattern.compile("(\\{(.*)\\})*");

    private static Pattern p1 = Pattern.compile("\\{");

    private static Pattern p2 = Pattern.compile("\\}");

    public static void main(String[] args) {

        initServer();

        String str = "";

        Map<String, Long> OlTime = Maps.newHashMap();
        Map<String, Boolean> isOnline = Maps.newHashMap();
        Map<String, Boolean> levelOnline = Maps.newHashMap();
        Map<String, LogPlayer> players = Maps.newHashMap();

        //关卡顺序
        Map<String, Integer> order = Maps.newHashMap();
        List<String> blockIds = KOSDataConfigService.getDataConfigIdList(NormalBlock.class);
        for (String id : blockIds) {
            order.put(id, blockIds.indexOf(id));
        }
        try {
            //统计24日登录过的玩家
            BufferedReader brRids = new BufferedReader(new FileReader(pathRids));
            Set<String> rids = Sets.newHashSet();
            while ((str = brRids.readLine()) != null) {
                rids.add(str);
            }
            brRids.close();

            int line = 0;

            BufferedReader br = new BufferedReader(new FileReader(path1));
            while ((str = br.readLine()) != null) {
                line++;
                if (line % 500000 == 0) {
                    System.out.println(line);
                }
                //                if (line > 500000) {
                //                    break;
                //                }

                int index1 = str.indexOf("#") - 6;
                if (index1 < 0) {
                    System.out.println(str);
                    continue;
                }
                String rid = str.substring(index1, index1 + 16);
                LogPlayer logPlayer;
                if (!players.containsKey(rid)) {
                    logPlayer = new LogPlayer();
                    logPlayer.setR(rid);
                } else {
                    logPlayer = players.get(rid);
                }

                if (rids.contains(rid)) {
                    continue;
                }
                if (str.contains("\t13001\t")) {
                    int index2 = str.indexOf("isWin") + 7;
                    String isWin = str.substring(index2, index2 + 4);
                    if ("true".equals(isWin)) {
                        int index3 = str.indexOf("\"point\":");
                        int index33 = str.indexOf("\"", index3 + 9);
                        String pointId = str.substring(index3 + 9, index33);
                        if (pointId.startsWith("M")) {
                            logPlayer.updateM(pointId, order);
                        } else if (pointId.startsWith("E")) {
                            logPlayer.updateE(pointId, order);
                        }
                    }
                } else if (str.contains("\t11001\t")) {
                    int index4 = str.indexOf("\"taskId\"") + 10;
                    int index5 = str.indexOf("\"", index4);
                    String taskId = str.substring(index4, index5);
                    TaskConfig taskConfig = KOSDataConfigService
                            .getSettingByIdNoException(TaskConfig.class, taskId);
                    if (null != taskConfig) {
                        if (taskConfig.taskType == TaskType.DAILY.value()) {
                            logPlayer.addTs(taskId);
                        }
                    }
                }

                int length = str.length();
                //统计游戏总时长
                if (!isOnline.getOrDefault(rid, false) && !str.contains("\t1007\t")) {
                    long time = Integer.valueOf(str.substring(length - 10, length));
                    OlTime.put(rid, time - OlTime.getOrDefault(rid, 0L));
                    isOnline.put(rid, true);

                    logPlayer.getL_t().put(logPlayer.getLevel() + 1,
                            time - logPlayer.getL_t().getOrDefault(logPlayer.getLevel() + 1, 0L));
                } else if (isOnline.getOrDefault(rid, false) && str.contains("\t1007\t")) {
                    long time = Integer.valueOf(str.substring(length - 10, length));
                    OlTime.put(rid, time - OlTime.getOrDefault(rid, time));
                    isOnline.put(rid, false);

                    logPlayer.getL_t().put(logPlayer.getLevel() + 1,
                            time - logPlayer.getL_t().getOrDefault(logPlayer.getLevel() + 1, 0L));
                } else if (isOnline.getOrDefault(rid, false)) {
                    //统计升级所用时间
                    int indexDiff;
                    if ((indexDiff = str.indexOf("\"diff\":")) != -1
                    //   && rid.equals("testcb#11057_301")
                    ) {
                        int indexLevel = indexDiff;
                        int level = 1;
                        while ((indexLevel = str.indexOf("\"level\"", indexLevel) + 1) != 0) {
                            if (checkDiffLevel(str.substring(indexDiff, indexLevel)) == 1) {
                                int index7 = str.indexOf(",", indexLevel);
                                level = Integer.valueOf(str.substring(indexLevel + 7, index7));
                                long time = Integer.valueOf(str.substring(length - 10, length));
                                logPlayer.getL_t().put(level,
                                        time - logPlayer.getL_t().getOrDefault(level, 0L));
                                logPlayer.getL_t().put(level + 1, time);
                                logPlayer.setLevel(level);

                                break;
                            }
                        }

                    }
                }

                players.put(rid, logPlayer);
            }

            br.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(path2));

            for (Map.Entry<String, Long> entry : OlTime.entrySet()) {
                if (isOnline.get(entry.getKey())) {
                    entry.setValue(FINTIME - entry.getValue());
                }
                players.get(entry.getKey()).setT(entry.getValue());
            }

            for (LogPlayer player : players.values()) {
                player.getL_t().remove(player.getLevel() + 1);
            }

            bw.write(
                    "rid                等级     普通关卡ID   精英关卡ID  23日的游戏总时长      当日完成的日常任务ID           到达x级花费的时间 \n");
            for (LogPlayer player : players.values()) {
                bw.write(player.toString());
                bw.flush();
            }
            bw.close();
            System.out.println("done!!!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("!!!!!! " + str);
        }

    }

    /**
     * 检查层级
     *
     * @param [str, p1, p2]
     * @return boolean
     * @Author: shenpeng
     * @Date: 2019-04-29
     */
    public static int checkDiffLevel(String str) {

        Matcher m1 = p1.matcher(str);
        int count1 = 0;
        while (m1.find()) {
            count1++;
        }

        Matcher m2 = p2.matcher(str);
        int count2 = 0;
        while (m2.find()) {
            count2++;
        }

        return count1 - count2;
    }

    public static void initServer() {
        initLogger();
        KOSLogger.outputEmptyLog();
        String type = MoreObjects.firstNonNull(System.getProperty("server.type"), "GS");
        System.setProperty("server.type", type);
        NetConfig.LOGIN_METHOD_OPTYPE = KOSOpCode.PLAYER_HANDLER_LOGIN_1002;
        NetConfig.DISCONNECT_METHOD_OPTYPE = KOSOpCode.PLAYER_HANDLER_DISCONNECT_1007;

        BaseAgent agent = null;
        try {
            KOSServerType serverType = KOSServerType.getServerType(type);
            RuntimeCfg.initSubClazz(serverType.runtimeClz());
            agent = serverType.agent();
            agent.init();
            //agent.start();
        } catch (Throwable e) {
            String msg = String.format("Start server failed, type:%s", type);
            KOSLogger.ERROR.error(msg, e);
            if (agent != null) {
                ((KOSGSAgent) agent).exit();
            }
            System.exit(1);
        }

    }

    private static void initLogger() {
        if (StringUtils.isEmpty(System.getProperty("server.home"))) {
            System.setProperty("server.home", System.getProperty("user.dir"));
        }

        String configFileStr = System.getProperty("server.home") + "/config/logback.xml";
        if (System.getProperty("server.config") != null) {
            configFileStr = System.getProperty("server.config") + "/logback.xml";
        }
        System.getProperties().setProperty("logback.configurationFile", configFileStr);

        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            LoggerFactory.getLogger(XMLBean.ErrorLogger.class)
                    .error("Uncaught Exception in thread '" + t.getName() + "'", e);
            LoggerFactory.getLogger(XMLBean.ErrorLogger.class).error(e.getMessage(), e);
        });
    }

}
