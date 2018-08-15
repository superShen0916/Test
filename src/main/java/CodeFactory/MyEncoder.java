package CodeFactory;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class MyEncoder extends ProtocolEncoderAdapter{
	private final Charset charset;  
	  
    public MyEncoder(Charset charset) {  
        this.charset = charset;  
    }  
    
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		String value=(String) message;
		IoBuffer buf=IoBuffer.allocate(value.getBytes().length);
		buf.setAutoExpand(true);
		if (value!=null) {
			buf.put(value.trim().getBytes());
		}
		buf.flip();
		out.write(buf);
	}
	
}
