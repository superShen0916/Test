package paytestlog.lostplayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.tool.XMLBean;
import org.slf4j.LoggerFactory;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;
import com.playcrab.common.agent.BaseAgent;
import com.playcrab.common.config.RuntimeCfg;
import com.playcrab.kos.agents.KOSGSAgent;
import com.playcrab.kos.common.config.KOSServerType;
import com.playcrab.kos.common.logger.KOSLogger;
import com.playcrab.kos.gs.enums.KOSOpCode;
import com.playcrab.net.mina.typical.NetConfig;

/**
 * @Description: 筛选用户范围 23日注册的玩家
 *               最后的等级
 *               即得到玩家当日最高等级
 * @Author: shenpeng
 * @Date: 2019-04-29
 */
public class PlayerLostInfo3 {

    private static final String path1 = "/Volumes/macwin/log/action_log_2019-04-23-24/action_log_2019-04-23.log";

    /**
     * 每个玩家最后的等级
     */
    private static final String path2 = "/Volumes/macwin/log/action_log_2019-04-23-24/player23/rid-level.log";

    /**
     * 每个等级有多少人
     */
    private static final String path3 = "/Volumes/macwin/log/action_log_2019-04-23-24/player23/level-count.log";

    private static Pattern p1 = Pattern.compile("\\{");

    private static Pattern p2 = Pattern.compile("\\}");

    public static void main(String[] args) {

        //   initServer();

        String str = "";

        Map<String, Integer> rid_level = Maps.newHashMap();

        try {

            int line = 0;

            BufferedReader br = new BufferedReader(new FileReader(path1));
            while ((str = br.readLine()) != null) {
                line++;
                if (line % 500000 == 0) {
                    System.out.println(line);
                }

                int index1 = str.indexOf("#") - 6;
                if (index1 < 0) {
                    System.out.println(str);
                    continue;
                }
                String rid = str.substring(index1, index1 + 16);

                int indexDiff;
                if ((indexDiff = str.indexOf("\"diff\":")) != -1) {
                    int indexLevel = indexDiff;
                    int level;
                    while ((indexLevel = str.indexOf("\"level\"", indexLevel) + 1) != 0) {
                        if (checkDiffLevel(str.substring(indexDiff, indexLevel)) == 1) {
                            int index7 = str.indexOf(",", indexLevel);
                            level = Integer.valueOf(str.substring(indexLevel + 7, index7));
                            if (level < rid_level.getOrDefault(rid, 0)) {
                                System.out.println(str);
                            }
                            rid_level.put(rid, level);
                            break;
                        }
                    }

                }

            }

            br.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(path2));

            bw.write("rid                等级    \n");
            for (Map.Entry<String, Integer> entry : rid_level.entrySet()) {
                bw.write(entry.getKey() + "          " + entry.getValue() + "\n");
                bw.flush();
            }
            bw.close();

            //            BufferedWriter bw3 = new BufferedWriter(new FileWriter(path3));
            //            bw3.write("等级     数量+\n");
            //            for (int i = 0; i < 50; i++) {
            //                int count = 0;
            //                for (Map.Entry<String, Integer> entry : rid_level.entrySet()) {
            //                    if (entry.getValue() == i) {
            //                        count++;
            //                    }
            //                }
            //                if (count != 0) {
            //                    bw3.write(i + "   " + count + "\n");
            //                    bw3.flush();
            //                }
            //            }
            //            bw3.closeBoss();
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
