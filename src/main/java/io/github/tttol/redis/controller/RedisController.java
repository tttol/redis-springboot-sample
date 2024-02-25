package io.github.tttol.redis.controller;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
@RequestMapping("redis")
public class RedisController {
    @SuppressWarnings("null")
    @GetMapping
    public String hello() {
        var connectionFactory = new LettuceConnectionFactory();
		connectionFactory.afterPropertiesSet();

		var template = new RedisTemplate<String, String>();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultSerializer(StringRedisSerializer.UTF_8);
		template.afterPropertiesSet();

		template.opsForValue().set("foo", "bar");

		log.info("Value at foo:" + template.opsForValue().get("foo"));

		connectionFactory.destroy();
        return "Hello, Redis!";
    }    
}
