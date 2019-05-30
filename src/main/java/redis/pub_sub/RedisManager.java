package redis.pub_sub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description: Redis管理类
 * @Author: shenpeng
 * @Date: 2019-05-27
 */
public class RedisManager {

    private static JedisPool jedisPool;

    public static void init(int maxIdle, int maxTotal, int maxWaitMills, String host, int port,
            String password, int database) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMills);
        if ("".equals(password)) {
            jedisPool = new JedisPool(jedisPoolConfig, host, port, maxWaitMills, null, database);
        } else {
            jedisPool = new JedisPool(jedisPoolConfig, host, port, maxWaitMills, password,
                    database);
        }
    }

    public static Jedis getClient() {
        if (jedisPool == null) {
            System.out.println("UnInitialized");
            return null;
        }
        return jedisPool.getResource();
    }

}
