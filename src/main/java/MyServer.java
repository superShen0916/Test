import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyServer {
    public static void main(String[] args) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        acceptor.setHandler(new MyHandler());
        acceptor.getFilterChain().addLast("codec", 
                new ProtocolCodecFilter(
                        new TextLineCodecFactory(Charset.forName("UTF-8")
                                ,LineDelimiter.WINDOWS.getValue(),LineDelimiter.WINDOWS.getValue())));
        acceptor.bind(new InetSocketAddress(9123));

    }

}
class MyHandler extends IoHandlerAdapter{
    private final static Logger log = LoggerFactory.getLogger(MyHandler.class);
    private final static String QUIT = "quit";
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String str = message.toString();
        log.info("this message received is [" + str + "]");
        if(QUIT.equals(str)) {
            session.close(true);
            return;
        }
    }
}