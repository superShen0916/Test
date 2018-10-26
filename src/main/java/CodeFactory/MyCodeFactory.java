package CodeFactory;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MyCodeFactory implements ProtocolCodecFactory {

    private final MyDecoder decoder;

    private final MyEncoder encoder;

    public MyCodeFactory(Charset charset) {
        encoder = new MyEncoder(charset);
        decoder = new MyDecoder(charset);
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }

}
