package readlog;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2018/12/24
 */
public class NioreadLog {

    static String path1 = "/Volumes/macwin/action日志/11-30-tower.log";

    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        try {
            FileChannel fileChannel = new RandomAccessFile(path1, "r").getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1112);
            byte[] temp = new byte[0];
            byte[] bs;
            int startNum, count = 0;
            boolean isNewLine = false;
            String str;
            while (fileChannel.read(byteBuffer) != -1) {
                bs = new byte[byteBuffer.position()];
                byteBuffer.flip();
                byteBuffer.get(bs);
                byteBuffer.clear();
                //                System.out.println(new String(bs));
                //                if (true) {
                //
                //                    break;
                //                }
                startNum = 0;
                for (int i = 0; i < bs.length; i++) {
                    if (bs[i] == 10) {
                        isNewLine = true;
                        startNum = i;
                    }
                }
                if (isNewLine) {
                    byte[] toTemp = new byte[temp.length + startNum];
                    System.arraycopy(temp, 0, toTemp, 0, temp.length);
                    System.arraycopy(bs, 0, toTemp, temp.length, startNum);
                    temp = new byte[bs.length - startNum - 1];
                    System.arraycopy(bs, startNum + 1, temp, 0, bs.length - startNum - 1);
                    str = new String(toTemp);
                    if (str.contains("10035")) {
                        count++;
                    }
                } else {
                    byte[] toTemp = new byte[temp.length + bs.length];
                    System.arraycopy(temp, 0, toTemp, 0, temp.length);
                    System.arraycopy(bs, 0, toTemp, temp.length, bs.length);
                    temp = toTemp;
                }
            }
            System.out.println(count);
            long time2 = System.currentTimeMillis();
            System.out.println("t: " + (time2 - time1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
