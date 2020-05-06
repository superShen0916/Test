package redis.pub_sub;

import redis.clients.jedis.Jedis;

/**
 * @Description: Redis发布消息
 * @Author: shenpeng
 * @Date: 2019-05-27
 */
public class Pub {

    public static void main(String[] args) {
        RedisManager.init(1000, 1000, 1000, "127.0.0.1", 6379, "", 1);

        Jedis jedis = RedisManager.getClient();

        jedis.publish("hello_redis", "hello redis");
        jedis.publish("hello_redis", "hello redis2");
    }

}
