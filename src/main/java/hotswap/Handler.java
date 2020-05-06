package hotswap;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-05-06
 */
public class Handler {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        ((ScheduledThreadPoolExecutor) threadPoolExecutor).scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                Service.print();
            }
        }, 30, 5, TimeUnit.SECONDS);

        //监听文件变化
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    ClassFileReader.INSTANCE.start("/Users/playcrab/Documents/reload");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
