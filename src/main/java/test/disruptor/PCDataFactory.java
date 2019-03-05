package test.disruptor;

import com.alipay.disruptor.EventFactory;

/**
 * @Description: 产生PCData的工厂类
 * @Author: shenpeng
 * @Date: 2019-03-05
 */
public class PCDataFactory implements EventFactory<PCData> {

    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
