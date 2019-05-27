package NIOTest4;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import net.sf.json.JSONObject;

public class MyClient {

    /** 15秒发送一次心跳包 */
    private static final int HEARTBEATRATE = 15;

    /** 心跳包内容 */
    private static final String HEARTBEATREQUEST = "100";// 预设请求内容

    private static final String HEARTBEATRESPONSE = "200";// 预设应答内容（记得客户端在接收到预设请求内容回复给服务端一定也是这个内容哦）
    // // eclipse dev change
    //
    // // chang3
    // // cahnge4
    // =======3
    // // change2
    // >>>>>>> branch 'Dev' of git@39.104.58.77:penn/eclipseTest.git

    @Override
    public String toString() {
        return super.toString();
    }

    public MyClient() {
    }

    public static NioSocketConnector connector;

    public static void main(String[] args) throws Exception {
        //        String udpMsg = "udp msg";
        //        DatagramSocket datagramSocket = new DatagramSocket();
        //        datagramSocket.connect(new InetSocketAddress("localhost", 8487));
        //        datagramSocket.send(new DatagramPacket(udpMsg.getBytes(), udpMsg.getBytes().length));

        connector = new NioSocketConnector();
        //NioDatagramConnector connector = new NioDatagramConnector();
        // connector.setConnectTimeoutMillis(20000);

        //SSLContext sslContext = new SSLContextGenerator().getSslContext();
        //SslFilter sslFilter = new SslFilter(sslContext);
        // sslFilter.setUseClientMode(true);
        // connector.getFilterChain().addFirst("sslFilter", sslFilter);
        connector.getFilterChain().addLast("codes", new ProtocolCodecFilter(new MyCodeFactory()));
        connector.setHandler(new MyClientHandler());

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

        connector.getFilterChain().addLast("heartbeat", heartBeat);
        // 心跳检测结束

        ConnectFuture future = connector.connect(new InetSocketAddress("localhost", 8487));
        future.awaitUninterruptibly();
        IoSession session = null;
        session = future.getSession();
        session.getConfig().setUseReadOperation(true);

        MyMessage gm = new MyMessage();
        Map<String, String> map = new HashMap<String, String>();

        gm.setCommand(1001);
        map.put("name", "bird");
        map.put("age", "7");

        System.out.println(JSONObject.fromObject(map).toString());
        gm.setContents(JSONObject.fromObject(map).toString().getBytes());
        session.write(gm);

        // connector.dispose();

    }

    private static class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

        @Override
        public boolean isRequest(IoSession ioSession, Object message) {

            // System.out.println("客户端判断消息是否为请求包消息: " + message);
            // if (message.equals(HEARTBEATREQUEST)) {
            // System.out.println("是请求信息");
            // return true;
            // }
            return false;
        }

        @Override
        public boolean isResponse(IoSession session, Object message) {
            // System.out.println("客户端判断响应心跳包信息: " + message);
            // MyMessage gm = (MyMessage) message;
            // System.out.println("gm.getCommand():" + gm.getCommand());
            // if (gm.getCommand() == Integer.valueOf((HEARTBEATRESPONSE))) {
            // System.out.println("是响应信息");
            // return true;
            // }
            // // if (message.equals(HEARTBEATRESPONSE))
            // // return true;
            return true;
        }

        @Override
        public Object getRequest(IoSession session) {
            System.out.println("服务端发送给客户端的心跳包消息: " + HEARTBEATREQUEST);
            // session.write(HEARTBEATREQUEST);
            //    connector.dispose();
            return HEARTBEATREQUEST;
            // return null;
        }

        @Override
        public Object getResponse(IoSession session, Object request) {
            // System.out.println("响应预设信息: " + HEARTBEATRESPONSE);
            return HEARTBEATRESPONSE;
            // return null;
        }

    }

}
