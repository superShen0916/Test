package readlog;

import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @Description: MappedByteBuffer性能测试
 * @Author: shenpeng
 * @Date: 2019/1/3
 */
public class NioMappedRead {

    private static String path = "/Volumes/macwin/action日志/action_log_2018-12-01.log";

    private static String path2 = "/Users/playcrab/Desktop/log/Test_ac";

    private static final String UID = "4405800";

    private static final int LEN = 1000;

    public static void main(String[] args) {
        int line = 0;
        try {

            FileChannel fc = new FileInputStream(path).getChannel();

            //  BufferedWriter bw = new BufferedWriter(new FileWriter(path2));
            long t1 = System.currentTimeMillis();
            byte[] bs;
            byte[] temp = new byte[0];
            String str;
            Map<String, Integer> map = Maps.newHashMap();

            long prePos = 0;

            int count = (int) (fc.size() / LEN + 1);
            int offset = LEN;
            long size = fc.size();

            for (int c = 0; c < count; c++) {
                prePos += offset;
                if (size - prePos < LEN) {
                    offset = (int) (size - prePos - 1);
                    System.out.println("=====" + offset);
                }
                MappedByteBuffer mappedByteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, prePos,
                        offset);
                bs = new byte[offset];
                mappedByteBuffer.get(bs);
                mappedByteBuffer.clear();
                int start = 0;
                for (int i = 0; i < bs.length; i++) {
                    if (bs[i] == 10) {
                        byte[] toTemp = new byte[temp.length + i - start];
                        System.arraycopy(temp, 0, toTemp, 0, temp.length);
                        System.arraycopy(bs, start, toTemp, temp.length, i - start);
                        start = i;
                        temp = new byte[0];
                        str = new String(toTemp);
                        line++;
                        //                        if (line >= 500000) {
                        //                            throw new RuntimeException();
                        //                        }

                    }
                }
                //将最后不是一行的字符存起来下一次读取的时候用
                if (start < bs.length - 1) {
                    temp = new byte[bs.length - 1 - start];
                    System.arraycopy(bs, start, temp, 0, bs.length - 1 - start);
                }
            }
            long t2 = System.currentTimeMillis();
            System.out.println("\n" + (t2 - t1));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("---------" + line);

        }
    }
}
