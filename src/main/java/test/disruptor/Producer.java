package test.disruptor;

import java.nio.ByteBuffer;

import com.alipay.disruptor.RingBuffer;

/**
 * @Description: 生产者
 * @Author: shenpeng
 * @Date: 2019-03-05
 */
public class Producer {

    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer byteBuffer) {
        //Grab the next sequence
        long sequence = ringBuffer.next();
        try {
            //Get the entry in the Disruptor for the sequence
            PCData event = ringBuffer.get(sequence);
            //Fill with data
            event.setValue(byteBuffer.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
