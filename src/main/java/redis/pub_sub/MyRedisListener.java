package redis.pub_sub;

import redis.clients.jedis.JedisPubSub;

/**
 * @Description: 处理Redis消息的实现类
 * @Author: shenpeng
 * @Date: 2019-05-27
 */
public class MyRedisListener extends JedisPubSub {

    // 取得订阅的消息后的处理
    @Override
    public void onMessage(String channel, String message) {
        // TODO Auto-generated method stub
        System.out.println(channel + "=" + message);
    }

    // 取得按表达式的方式订阅的消息后的处理
    @Override
    public void onPMessage(String pattern, String channel, String message) {
        // TODO Auto-generated method stub
        System.out.println(pattern + ":" + channel + "=" + message);
    }

    // 初始化订阅时候的处理
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        // TODO Auto-generated method stub
        System.out.println("初始化 【频道订阅】 时候的处理  ");
    }

    // 取消订阅时候的处理
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        // TODO Auto-generated method stub
        System.out.println("// 取消 【频道订阅】 时候的处理  ");
    }

    // 初始化按表达式的方式订阅时候的处理
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        // TODO Auto-generated method stub
        System.out.println("初始化 【模式订阅】 时候的处理  ");
    }

    // 取消按表达式的方式订阅时候的处理
    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        // TODO Auto-generated method stub
        System.out.println("取消 【模式订阅】 时候的处理  ");
    }

    @Override
    public void unsubscribe() {
        super.unsubscribe();
    }

    @Override
    public void unsubscribe(String... channels) {
        super.unsubscribe(channels);
    }

    @Override
    public void subscribe(String... channels) {
        super.subscribe(channels);
    }

    @Override
    public void psubscribe(String... patterns) {
        super.psubscribe(patterns);
    }

    @Override
    public void punsubscribe() {
        super.punsubscribe();
    }

    @Override
    public void punsubscribe(String... patterns) {
        super.punsubscribe(patterns);
    }
}
