package disruptor;

import com.alipay.disruptor.WorkHandler;

/**
 * @Description: 消费者
 * @Author: shenpeng
 * @Date: 2019-03-05
 */
public class Consumer implements WorkHandler<PCData> {

    @Override
    public void onEvent(PCData event) throws Exception {
        System.out.println(Thread.currentThread().getId() + "Event: -- "
                + event.getValue() * event.getValue() + " -- ");
    }
}
