package io.github.tttol.redis.service;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisService {
    @SuppressWarnings("null")
    public void put(@NonNull String key, @NonNull String value) {
        var connectionFactory = new LettuceConnectionFactory();
		connectionFactory.afterPropertiesSet();
		log.info("Redis host: %s".formatted(connectionFactory.getHostName()));
		log.info("Redis port: %d".formatted(connectionFactory.getPort()));

		var template = new RedisTemplate<String, String>();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultSerializer(StringRedisSerializer.UTF_8);
		template.afterPropertiesSet();
		template.opsForValue().set(key, value);

		log.info("Value at %s: %s".formatted(key, template.opsForValue().get(key)));

		connectionFactory.destroy();
    }
}
