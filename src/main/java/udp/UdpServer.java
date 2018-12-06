package udp;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

import NIOTest4.MyServerHandler;

/**
 * @Description: udp server
 * @Author: shenpeng
 * @Date: 2018/11/14
 */
public class UdpServer {

    public static void main(String[] args) throws IOException {
        //        DatagramSocket datagramSocket = new DatagramSocket(8889);
        IoAcceptor acceptor = new NioDatagramAcceptor();
        acceptor.setHandler(new MyServerHandler());
        acceptor.bind(new InetSocketAddress(8889));
    }
}
