package NIOTalkTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MainServer {
    private static final int Port=8899;
    public static void main(String[] args) {
    	Map<Long,IoSession> map=new HashMap<Long, IoSession>();
        IoAcceptor ioAcceptor=new NioSocketAcceptor();
        System.out.println("begin server....");
        ioAcceptor.getFilterChain().addLast("logger", new LoggingFilter());
        ioAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        ioAcceptor.setHandler(new HelloWorldHandler());
        ioAcceptor.getSessionConfig().setReadBufferSize(2048);
        ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        
        try {
            ioAcceptor.bind(new InetSocketAddress(Port));
//            System.out.println("000");
//            for (Long k: ioAcceptor.getManagedSessions().keySet()){
//            	ioAcceptor.getManagedSessions().get(k).write("11111111111111");
//            }
           
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        HelloWorldHandler handler=new HelloWorldHandler();
//        while (true) {
//        	handler.sentToClient();
//		}
       
    }
}