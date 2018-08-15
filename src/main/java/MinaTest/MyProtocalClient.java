package MinaTest;

import java.io.BufferedReader;  
import java.io.DataOutputStream;  
import java.io.InputStreamReader;  
import java.net.Socket;  
  
public class MyProtocalClient {  
    // mina编解码流程：request->MyDecoder->MyHandler->MyEncode->response  
    // 对于长度>1000字节的数据包采用分段发送。  
    public static void main(String[] args) {  
        try {  
            Socket socket = new Socket("127.0.0.1", 8083);  
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());  
            BufferedReader in = new BufferedReader(inputStreamReader);  
            for (int i = 0; i < 200; i++) {  
                long startTime = System.currentTimeMillis(); // 获取开始时间  
                String str = "123";  
                String temp_ = "";  
                int SUB_COUNT = 2;// 按1000个字节来截取字符串  
                // 发送数据包长度  
                out.writeInt(2608 + 8);  
                // 分段来发送数据  
                for (int c = 0; c < str.getBytes().length / SUB_COUNT; c++) {  
                    if (temp_ == "" && temp_.length() == 0) {  
                        temp_ = str.substring(c * SUB_COUNT, SUB_COUNT);  
                        // 发送第一次也是第一段数据  
                        out.write(temp_.getBytes());  
                    } else {  
                        // 按1000个字节分段发送数据  
                        out.write(str.substring(c * SUB_COUNT, (c + 1) * SUB_COUNT).getBytes());  
  
                        // 发送剩余的不足1000个字节的数据串  
                        int raim_ = str.substring((c + 1) * SUB_COUNT, str.getBytes().length).length();  
                        if (raim_ < SUB_COUNT && raim_ != 0) {  
                            out.write(str.substring((c + 1) * SUB_COUNT, str.getBytes().length).getBytes());  
                        }  
                    }  
                }  
                // 马上写入，释放缓存  
                out.flush();  
                System.out.println(i + " sended");  
  
                char temp[] = new char[2700];  
                String backLine = "";  
                in.read(temp);  
                backLine = String.valueOf(temp).trim();  
                System.out.println("backLine==" + backLine);  
                long endTime = System.currentTimeMillis(); // 获取结束时间  
                System.out.println("程序运行时间(毫秒)： " + (endTime - startTime) + "ms");  
            }  
            Thread.sleep(1000);  
            out.close();  
            in.close();  
            socket.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  
