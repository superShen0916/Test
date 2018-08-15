package test.futurepattern;

/**
 * Future模式 和代理模式的区别可能是用异步的方式处理请求，如果是代理模式也需要等待，但Future模式在等待的时候可以干别的事
 *
 * @date 2018-08-15
 * @author shenpeng
 */
public class Client {

    public Data requset(final String queryStr) {
        final FutureData futureData = new FutureData();
        new Thread() {

            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }.start();
        return futureData;
    }
}
