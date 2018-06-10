package com.mybatis.redis;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.ibatis.cache.Cache;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @author WHD data 2016年5月22日
 */
public class MybatisRedisCacheWithJredis implements Cache {
  private Jedis redisClient = getJedis();

  /** The ReadWriteLock. */
  private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  private String id;

  private static JedisPool pool;

  // Redis服务器IP
  private static String ADDR = "127.0.0.1";

  // Redis的端口号
  private static int PORT = 6379;

  // 访问密码
  private static String AUTH = "123";

  private static int TIMEOUT = 10000;

  /**
   * 初始化Redis连接池
   */
  static {
    try {
      JedisPoolConfig config = getJedisConfig();
      pool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
      // pool = new JedisPool(config, ADDR, PORT);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public MybatisRedisCacheWithJredis(final String id) {
    System.out.println("id : " + id);
    if (id == null) {
      throw new IllegalArgumentException("Cache instances require an ID");
    }
    this.id = id;
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public int getSize() {
    // 获取 缓存key的大小
    return Integer.valueOf(redisClient.dbSize().toString());
  }

  @Override
  public void putObject(Object key, Object value) {
    System.err.println("put key" + key);
    redisClient.set(SerializeUtil.serialize(key.toString()), SerializeUtil.serialize(value));
  }

  @Override
  public Object getObject(Object key) {
    System.out.println("get key");
    Object value =
        SerializeUtil.unserialize(redisClient.get(SerializeUtil.serialize(key.toString())));
    return value;
  }

  @Override
  public Object removeObject(Object key) {
    System.err.println("remove key" + key);
    return redisClient.expire(SerializeUtil.serialize(key.toString()), 0);
  }

  // 在进行 insert update delete 时执行
  @Override
  public void clear() {
    System.err.println("clear all data");
    redisClient.flushDB();
  }

  @Override
  public ReadWriteLock getReadWriteLock() {
    return readWriteLock;
  }

  public static Jedis getJedis() {
    // pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379);
    Jedis jedis = pool.getResource();
    // jedis.auth("123");
    return jedis;
  }

  protected static JedisPoolConfig getJedisConfig() {
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

    //连接超时时是否阻塞，false时报异常,ture阻塞直到超时, 默认true
    jedisPoolConfig.setBlockWhenExhausted(true);

    //逐出策略（默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)）
    jedisPoolConfig.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");

    //最大空闲连接数, 默认8个
    jedisPoolConfig.setMaxIdle(8);

    //最大连接数, 默认8个
    jedisPoolConfig.setMaxTotal(8);

    //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
    jedisPoolConfig.setMaxWaitMillis(-1);

    //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
    jedisPoolConfig.setMinEvictableIdleTimeMillis(1800000);

    //最小空闲连接数, 默认0
    jedisPoolConfig.setMinIdle(0);

    //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
    jedisPoolConfig.setNumTestsPerEvictionRun(3);

    //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)   
    jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(1800000);

    // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,
    // 默认-1
    jedisPoolConfig.setMaxWaitMillis(100);

    //对拿到的connection进行validateObject校验
    jedisPoolConfig.setTestOnBorrow(true);

    //在进行returnObject对返回的connection进行validateObject校验
    jedisPoolConfig.setTestOnReturn(true);

    //定时对线程池中空闲的链接进行validateObject校验
    jedisPoolConfig.setTestWhileIdle(true);

    return jedisPoolConfig;
  }
}
