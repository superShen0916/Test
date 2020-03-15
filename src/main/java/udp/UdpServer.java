package udp;

import java.io.IOException;

/**
 * @Description: udp server
 * @Author: shenpeng
 * @Date: 2018/11/14
 */
public class UdpServer {

    public static void main(String[] args) throws IOException {

        UdpClient udpClient = new UdpClient();

        //        //        DatagramSocket datagramSocket = new DatagramSocket(8889);
        //        IoAcceptor acceptor = new NioDatagramAcceptor();
        //        acceptor.setHandler(new MyServerHandler());
        //
        //        acceptor.bind(new InetSocketAddress(8889));
    }
}
