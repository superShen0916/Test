package NIOTest4;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.xerial.snappy.Snappy;

public class MyEncoder implements ProtocolEncoder {

    public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
            throws Exception {

        IoBuffer buffer = null;
        // change

        if (message instanceof String) {
            buffer = IoBuffer.allocate(1024);
            buffer.setAutoExpand(true);
            MyMessage msg = new MyMessage();
            System.out.println("message Encode: message=" + message);
            // 编码数据结构的A，时间戳
            // buffer.put(getTimeTag(msg.getDate()));
            // 数据结构的B
            byte[] bs = Snappy.compress(((String) message).getBytes());
            buffer.putInt(bs.length + 4);

            buffer.putInt(Integer.valueOf((String) message));
            buffer.put(Snappy.compress(((String) message).getBytes()));
            buffer.flip();
            System.out.println("编码结束");
        } else {
            // change end
            MyMessage msg = (MyMessage) message;

            byte[] bs = msg.getContents();
            System.out.println("bs.tostring" + bs.toString());
            byte[] compressed = Snappy.compress(bs);
            // 压缩
            System.out.println(compressed.length + 4);
            buffer = IoBuffer.allocate(compressed.length + 8);
            buffer.setAutoExpand(true);
            // 编码数据结构的A，时间戳
            // buffer.put(getTimeTag(msg.getDate()));

            // 数据结构的B
            buffer.putInt(compressed.length + 4);
            // 数据结构的C
            buffer.putInt(msg.getCommand());
            // 数据结构的D
            buffer.put(compressed);
            buffer.flip();
            System.out.println("编码成功");
        }

        out.write(buffer);
    }

    public void dispose(IoSession session) throws Exception {

    }

    public static byte[] getTimeTag(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar c = Calendar.getInstance();
        // c.get(Calendar.YEAR);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd-HH-mm-ss");
        String dateStr = dateFormat.format(date);

        // System.out.println(dateStr);
        String[] dates = dateStr.split("-");
        // System.out.println(dates);
        byte[] bt = new byte[dates.length];
        // System.out.println(dates.length);
        for (int a = 0; a < dates.length; a++) {
            // System.out.println(dates[a]);
            bt[a] = Byte.parseByte(dates[a]);
        }
        return bt;
    }

}