package io.github.tttol.redis.controller;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("redis")
@RequiredArgsConstructor
public class RedisController {
    private final HttpSession session;

    @GetMapping
    public String hello() {
        return "Hello, Redis!";
    }    

    @SuppressWarnings("null")
    @GetMapping("simple/put")
    public String put() {
        var connectionFactory = new LettuceConnectionFactory();
		connectionFactory.afterPropertiesSet();

		var template = new RedisTemplate<String, String>();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultSerializer(StringRedisSerializer.UTF_8);
		template.afterPropertiesSet();

		template.opsForValue().set("foo", "bar");

		log.info("Value at foo:" + template.opsForValue().get("foo"));

		connectionFactory.destroy();
        return "put foo=bar to redis";
    }    

    @GetMapping("session/put/{key}/{value}")
    public String putSession(@PathVariable String key, @PathVariable String value) {
        session.setAttribute(key, value);
        return "put %s=%s to session".formatted(key, value);
    }
}
