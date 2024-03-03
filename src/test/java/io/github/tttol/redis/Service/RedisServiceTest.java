package io.github.tttol.redis.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.redis.testcontainers.RedisContainer;

import io.lettuce.core.RedisClient;

@Testcontainers
class RedisExampleTest {

    
}

@Testcontainers
public class RedisServiceTest {
    @Container
    private static RedisContainer container = new RedisContainer(
            RedisContainer.DEFAULT_IMAGE_NAME.withTag(RedisContainer.DEFAULT_TAG)).withExposedPorts(16379);

    @Test
    void testSomethingUsingLettuce() {
        // Retrieve the Redis URI from the container
        String redisURI = container.getRedisURI();
        RedisClient client = RedisClient.create(redisURI);
        try (var connection = client.connect()) {
            var commands = connection.sync();
            Assertions.assertEquals("PONG", commands.ping());
        }
    }
}
