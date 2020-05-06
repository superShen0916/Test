package redis.pub_sub;

import redis.clients.jedis.Jedis;

/**
 * @Description: 订阅Redis频道
 * @Author: shenpeng
 * @Date: 2019-05-27
 */
public class Sub {

    public static void main(String[] args) {
        MyRedisListener listener = new MyRedisListener();
        RedisManager.init(1000, 1000, 1000, "127.0.0.1", 6379, "", 1);
        Jedis jedis = RedisManager.getClient();

        jedis.psubscribe(listener, "hello*");
        System.out.println(111); //这里不会输出,因为上一步会阻塞
    }

}
