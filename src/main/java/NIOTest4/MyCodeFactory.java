package NIOTest4;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MyCodeFactory implements ProtocolCodecFactory {

    private ProtocolDecoder decoder;
    private ProtocolEncoder encoder;

    public MyCodeFactory() {
        decoder = new MyDecoder();
        encoder = new MyEncoder();
    }

    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }

    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }
}