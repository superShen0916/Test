package paytestlog;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.tool.XMLBean;
import org.slf4j.LoggerFactory;

import com.google.common.base.MoreObjects;
import com.playcrab.common.agent.BaseAgent;
import com.playcrab.common.config.RuntimeCfg;
import com.playcrab.kos.agents.KOSGSAgent;
import com.playcrab.kos.common.config.KOSServerType;
import com.playcrab.kos.common.logger.KOSLogger;
import com.playcrab.kos.gs.enums.KOSOpCode;
import com.playcrab.net.mina.typical.NetConfig;

/**
 * @Description: 服务器相关
 * @Author: shenpeng
 * @Date: 2019-04-30
 */
public class KosServer {

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
