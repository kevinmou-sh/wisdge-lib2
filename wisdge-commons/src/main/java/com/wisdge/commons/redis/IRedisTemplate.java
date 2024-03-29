package com.wisdge.commons.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface IRedisTemplate {
	void set(String key, Object value);
	void set(String key, Object value, long ttl, TimeUnit timeUnit);

	void setGlobal(String key, Object value);
	void setGlobal(String key, Object value, long ttl, TimeUnit timeUnit);

	void putHash(String key, String hashKey, Object hashVaule);
	void putHashGlobal(String key, String hashKey, Object hashVaule);

	Object get(String key);
	Object getGlobal(String key);

	Map<Object, Object> entries(String key);
	Map<Object, Object> entriesGlobal(String key);

	Boolean expire(String key, long timeout, TimeUnit unit);
	Boolean expireGlobal(String key, long timeout, TimeUnit unit);
	Boolean expire(String key, Duration timeout);
	Boolean expireAt(String key, Instant expireAt);
	void restore(String key, byte[] value, long timeToLive, TimeUnit unit);

	Boolean delete(String key);
	Boolean deleteGlobal(String key);

	Long convertAndSend(String channel, Object obj);
	RedisConnectionFactory getConnectionFactory();

}
