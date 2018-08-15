package NIOTest4;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MyServerHandler extends IoHandlerAdapter {
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        System.out.println("there is sth wrong");
        session.close(true);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        // 收到了上面解码后的消息
        MyMessage msg = (MyMessage) message;
        if (message == null) {
            // TODO
            System.out.println("message is null");
        }

        int cmd = msg.getCommand();
        System.out.println("服务端收到了一个信息");

        System.out.println(msg.getCommand() + "-----" + new String(msg.getContents()));
        // session.write(msg);
    }
}