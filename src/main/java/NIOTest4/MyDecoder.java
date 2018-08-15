package NIOTest4;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.xerial.snappy.Snappy;

public class MyDecoder extends CumulativeProtocolDecoder {

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
            throws Exception {
        // 读取数据结构A
        byte[] dateTag = new byte[6];
        // in.get(dateTag);
        // 读取数据结构B
        int length = in.getInt();
        // 读取数据结构C
        int command = in.getInt();
        System.out.println("Decode Cpmmand=" + command);
        System.out.println(length);
        // 读取数据结构D
        byte[] bytes = new byte[length - 4];
        in.get(bytes);
        // System.out.println(bytes);
        // 提取出数据结构对象
        MyMessage msg = new MyMessage();
        msg.setCommand(command);
        byte[] uncompressed = Snappy.uncompress(bytes);
        msg.setContents(uncompressed);
        out.write(msg);
        return true;
    }
}
