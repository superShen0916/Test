package NIOTalkTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class HelloWorldHandler extends IoHandlerAdapter {

    public boolean flag = false;

    public IoSession session1 = null;

    public Map<Integer, IoSession> map = new HashMap<Integer, IoSession>();

    public void sentToClient() {

        System.out.println("sent to c");
        Scanner inScanner = new Scanner(System.in);
        String reply = inScanner.nextLine();
        map.get(1).write(reply);
        System.out.println("message have been written");

    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {

        map.put(1, session);
        session1 = session;
        flag = true;
        String string = message.toString();
        if (string.trim().equalsIgnoreCase("quit")) {
            session.close(true);
            return;
        }
        System.out.println("recevied message: " + string);

        //	 System.out.println("recevied message: "+object);
        Scanner inScanner = new Scanner(System.in);
        String reply = inScanner.nextLine();
        session.write(reply);
        System.out.println("message have been written");

    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("message have been sent");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("closed session");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("session created");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("IDLE " + session.getIdleCount(status));
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("session opened");
    }

}
