package disruptor;

/**
 * @Description: 主函数类
 * @Author: shenpeng
 * @Date: 2019-03-05
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        //        PCDataFactory pcDataFactory = new PCDataFactory();
        //        // Specify the size of the ring buffer, must be power of 2
        //        int bufferSize = 1024;
        //        Disruptor<PCData> disruptor = new Disruptor<PCData>(pcDataFactory, bufferSize,
        //                new DefaultThreadFactory("MyPool"), ProducerType.MULTI, new BlockingWaitStrategy());
        //        disruptor.handleEventsWithWorkerPool(new Consumer(), new Consumer(), new Consumer(),
        //                new Consumer());
        //        disruptor.start();
        //
        //        RingBuffer<PCData> ringBuffer = disruptor.getRingBuffer();
        //        Producer producer = new Producer(ringBuffer);
        //        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        //        for (long l = 0; true; l++) {
        //            byteBuffer.putLong(0, l);
        //            producer.pushData(byteBuffer);
        //            Thread.sleep(100);
        //            System.out.println("add data " + l);
        //        }
    }
}
