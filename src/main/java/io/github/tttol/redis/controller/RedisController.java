package io.github.tttol.redis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.tttol.redis.service.RedisService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("redis")
@RequiredArgsConstructor
public class RedisController {
    private final HttpSession session;
    private final RedisService redisService;

    @GetMapping
    public String hello() {
        return "Hello, Redis!";
    }    

    @GetMapping("simple/put/{key}/{value}")
    public String put(@PathVariable String key, @PathVariable String value) {
        redisService.put(key, value);
        return "put %s=%s to Redis".formatted(key, value);
    }    

    @GetMapping("session/put/{key}/{value}")
    public String putSession(@PathVariable String key, @PathVariable String value) {
        session.setAttribute(key, value);
        return "put %s=%s to session".formatted(key, value);
    }
}
