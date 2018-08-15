package NIOTest4;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MyClientHandler extends IoHandlerAdapter {

    private static final String HEARTBEATREQUEST = "100";
    private static final String HEARTBEATRESPONSE = "200";

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        // session.write(obj);
        System.out.println("open");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        // 收到消息，。。。
        MyMessage gm = (MyMessage) message;

        System.out.println("客户端收到一个信息");
        // if (gm.getCommand() == Integer.valueOf((HEARTBEATREQUEST))) {
        if (gm.getCommand() == Integer.valueOf(HEARTBEATREQUEST)) {
            System.out.println("客户端输出响应信息");
            session.write(HEARTBEATRESPONSE);
        }
        System.out.println("客户端收到的是：" + gm.getCommand() + ":" + new String(gm.getContents()));
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("The Server Closed This Connection");
        session.close(true);
    }
}
