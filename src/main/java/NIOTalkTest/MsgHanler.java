package NIOTalkTest;

import java.util.Scanner;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

public class MsgHanler extends IoHandlerAdapter {
    private static final Logger log = LoggerFactory.getLogger(MsgHanler.class);

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        // 出现异常
        log.error("--------exception--------");
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        // 从服务器中接收到消息后的处理
    	 String string=message.toString();
         if (string.trim().equalsIgnoreCase("quit")) {
             session.close(true);
             return;
         }
         System.out.println("recevied message: "+string);
     	
     //	 System.out.println("recevied message: "+object);
         Scanner inScanner=new Scanner(System.in);
         String reply=inScanner.nextLine();
         session.write(reply);
         System.out.println("message have been written");
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        // 往服务器中发送消息
        log.info("--------msg sent--------");
        super.messageSent(session, message);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        // 当session被创建的时候调用
        log.info("--------session create--------");
        super.sessionCreated(session);
    }
}
