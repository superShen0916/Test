package redis.lock;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @Description: 分布式锁
 * @Author: shenpeng
 * @Date: 2020-03-25
 */
public class RedisLock {

    private static final String NX = "NX";

    private static final String PX = "PX";

    private static final String LOCK_OK = "OK";

    private static final Long UN_LOCK_OK = 1L;

    /**
     * 加分布式锁
     * 
     * @param [randomKey, value, expireMills]
     * @return boolean
     * @Author: shenpeng
     * @Date: 2020-03-25
     */
    public boolean lock(Jedis jedis, String key, String requestId, long expireMills) {
        String result = jedis.set(key, requestId, NX, PX, expireMills);
        if (LOCK_OK.equals(result)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 加锁错误示范1
     * 不是原子操作
     * 
     * @param [jedis, randomKey, value, expireSeconds]
     * @return boolean
     * @Author: shenpeng
     * @Date: 2020-03-26
     */
    public boolean wrongLock1(Jedis jedis, String randomKey, String value, int expireSeconds) {
        Long result = jedis.setnx(randomKey, value);
        if (result == 1) {
            //如果这时候程序因为异常情况没有执行下去，那么这个key就不会过期了,之后就可能出现死锁
            jedis.expire(randomKey, expireSeconds);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 加锁错误示范2
     * 这里不设置过期时间，而是用过期时间戳作value，每次加锁的时候都判断有没有过期
     * 
     * @param [jedis, randomKey, expireMills]
     * @return boolean
     * @Author: shenpeng
     * @Date: 2020-03-26
     */
    public boolean wrongLock2(Jedis jedis, String key, long expireMills) {
        String newValue = String.valueOf(System.currentTimeMillis() + expireMills);
        // 如果当前锁不存在，返回加锁成功
        if (jedis.setnx(key, newValue) == 1) {
            return true;
        }
        //判断上一个锁是否已经过期
        String oldTime = jedis.get(key);
        if (oldTime != null && Long.valueOf(oldTime) < System.currentTimeMillis()) {
            //锁已经过期了，更新过期时间,但是要判断此时其它程序是否更新了锁
            String oldValue = jedis.getSet(key, newValue);
            if (oldTime.equals(oldValue)) {
                return true;
            }
        }
        //锁没有过期，或者其他线程更新了锁，返回失败
        return false;
    }

    /**
     * 解锁
     * 
     * @param [jedis, key, requestId]
     * @return boolean
     * @Author: shenpeng
     * @Date: 2020-03-26
     */
    public boolean unlock(Jedis jedis, String key, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(key),
                Collections.singletonList(requestId));

        if (UN_LOCK_OK.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 解锁的错误示例1
     * 不是锁的拥有者，也会解除锁
     * 
     * @param [jedis, key]
     * @return void
     * @Author: shenpeng
     * @Date: 2020-03-26
     */
    public void wrongUnlock1(Jedis jedis, String key) {
        jedis.del(key);
    }

    /**
     * 解锁的错误示例12
     * 不是原子操作
     * 
     * @param [jedis, key, requestId]
     * @return void
     * @Author: shenpeng
     * @Date: 2020-03-26
     */
    public void wrongUnlock2(Jedis jedis, String key, String requestId) {
        if (requestId.equals(jedis.get(key))) {
            //如果这时候，另一个程序加锁了，这个锁立马又被删了
            jedis.del(key);
        }
    }
}
