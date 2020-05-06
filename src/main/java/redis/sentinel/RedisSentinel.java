package redis.sentinel;

import com.google.common.collect.Sets;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Set;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-04-20
 */
public class RedisSentinel {

    public static void main(String[] args) {
        String masterName = "masterName";
        String pass = "pass";
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        Set<String> sentinels = Sets.newHashSet();
        sentinels.add(new HostAndPort("127.0.0.1", 26379).toString());
        sentinels.add(new HostAndPort("127.0.0.x", 26379).toString());
        sentinels.add(new HostAndPort("127.0.0.y", 26379).toString());
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, sentinels,
                jedisPoolConfig, 0, pass);
        jedisSentinelPool.getResource();
        jedisSentinelPool.getCurrentHostMaster().toString();
    }
}
