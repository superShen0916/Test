package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import NIOTest4.MyMessage;
import net.sf.json.JSONObject;

/**
 * @Description: udp client
 * @Author: shenpeng
 * @Date: 2018/11/14
 */
public class UdpClient {

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        MyMessage msg = new MyMessage();
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "bird");
        map.put("age", "7");
        msg.setContents(JSONObject.fromObject(map).toString().getBytes());
        DatagramPacket datagramPacket = new DatagramPacket(msg.getContents(),
                msg.getContents().length, new InetSocketAddress("localhost", 8889));
        datagramSocket.send(datagramPacket);
    }
}
