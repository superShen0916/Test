package NIOTest4;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MyServer {

    private static final int IDELTIMEOUT = 30;

    /** 15秒发送一次心跳包 */
    private static final int HEARTBEATRATE = 15;

    /** 心跳包内容 */
    private static final String HEARTBEATREQUEST = "100";// 预设请求内容

    private static final String HEARTBEATRESPONSE = "200";// 预设应答内容（记得客户端在接收到预设请求内容回复给服务端一定也是这个内容哦）

    public static void main(String[] args) {
        IoAcceptor accepter = new NioSocketAcceptor();

        accepter.setHandler(new MyServerHandler());
        accepter.getFilterChain().addLast("sslFilter",
                new SslFilter(new SSLContextGenerator().getSslContext()));

        ProtocolCodecFilter coderFilter =
                // 使用自定义的编码解码filter
                new ProtocolCodecFilter(new MyCodeFactory());
        // accepter.getFilterChain().addLast("compression", new
        // CompressionFilter());
        accepter.getFilterChain().addLast("codec", coderFilter);

        accepter.getFilterChain().addLast("logger", new LoggingFilter());

        // 设置数据将被读取的缓冲区大小
        // 绑定handler
        // 心跳检测开始
        KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();
        KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory, IdleStatus.BOTH_IDLE);

        // 设置是否forward到下一个filter
        heartBeat.setForwardEvent(true);
        // 设置心跳频率
        heartBeat.setRequestInterval(HEARTBEATRATE);
        // 设置失败处理handler
        heartBeat.setRequestTimeoutHandler(new KeepAliveRequestTimeoutHandlerImpl());
        accepter.getFilterChain().addLast("heartbeat", heartBeat);
        // 心跳检测结束

        accepter.getSessionConfig().setReadBufferSize(2048);
        accepter.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, IDELTIMEOUT);
        try {
            accepter.bind(new InetSocketAddress(8487));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

        @Override
        public boolean isRequest(IoSession ioSession, Object message) {
            // boolean isRequest(IoSession session, Object message);
            System.out.println("服务端判断消息是否为请求包消息: " + message);
            if (message instanceof String) {
                return false;
            }
            MyMessage gm = (MyMessage) message;
            if (gm.getCommand() == Integer.valueOf((HEARTBEATREQUEST))) {
                System.out.println("是请求信息");
                return true;
            }

            // if (message.equals(HEARTBEATREQUEST)) {
            // System.out.println("是请求信息");
            // return true;
            // }
            return false;
        }

        @Override
        public boolean isResponse(IoSession session, Object message) {
            // System.out.println("服务端判断响应心跳包信息: " + message);
            // MyMessage gm = (MyMessage) message;
            // System.out.println("gm.getCommand():" + gm.getCommand());
            // if (gm.getCommand() == Integer.valueOf((HEARTBEATRESPONSE))) {
            // System.out.println("是响应信息");
            // return true;
            // }
            // // if (message.equals(HEARTBEATRESPONSE))
            // // return true;
            return false;
        }

        @Override
        public Object getRequest(IoSession session) {
            // System.out.println("服务端发送给客户端的心跳包消息: " + HEARTBEATREQUEST);
            // // session.write(HEARTBEATREQUEST);
            // // return HEARTBEATREQUEST;
            return null;
        }

        @Override
        public Object getResponse(IoSession session, Object request) {
            // System.out.println("响应预设信息: " + HEARTBEATRESPONSE);
            return HEARTBEATRESPONSE;
            // return null;
        }

    }

    private static class KeepAliveRequestTimeoutHandlerImpl
            implements KeepAliveRequestTimeoutHandler {

        @Override
        public void keepAliveRequestTimedOut(KeepAliveFilter keepAliveFilter, IoSession ioSession)
                throws Exception {
            ioSession.close(true);
            System.out.print("客户端挂了  我把session给干掉了！");
        }
    }
}
