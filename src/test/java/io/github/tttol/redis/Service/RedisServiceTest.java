package io.github.tttol.redis.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.redis.testcontainers.RedisContainer;

import io.github.tttol.redis.service.RedisService;
import io.lettuce.core.RedisClient;

@Testcontainers
@SpringBootTest
public class RedisServiceTest {
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void close() throws Exception {
        closeable.close();
    }

    @Autowired
    private RedisService redisService;

    @Container
    static RedisContainer container = new RedisContainer(
            RedisContainer.DEFAULT_IMAGE_NAME.withTag(RedisContainer.DEFAULT_TAG));

    @Test
    void testConnection() {
        var client = RedisClient.create(container.getRedisURI());
        try (var connection = client.connect()) {
            var commands = connection.sync();
            assertEquals("PONG", commands.ping());
        }
    }

    @Test
    void testPut() {
        var client = RedisClient.create(container.getRedisURI());
        try (var connection = client.connect()) {
            redisService.put("key", "value");
            
            var commands = connection.sync();
            var actual = commands.get("key");
            assertEquals("value", actual);
        }
    }
}
