package io.github.tttol.redis.Service;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import io.lettuce.core.RedisClient;

@Testcontainers
public class RedisServiceTest {
    private static final int PORT = 6380; //6379はプロダクトコードで使用
    @Container
    static GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:6.2.5"))
        .withExposedPorts(PORT);

    private RedisClient redisClient;

    // @BeforeEach
    // void setup() {
    //     // 起動したコンテナの情報を元にRedisTemplateを生成
    //     var redisConfiguration = new RedisStandaloneConfiguration(redis.getHost(), redis.getMappedPort(PORT));
    //     var jedisConnectionFactory = new JedisConnectionFactory(redisConfiguration);
    //     jedisConnectionFactory.afterPropertiesSet();

    //     RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    //     redisTemplate.setConnectionFactory(jedisConnectionFactory);
    //     redisTemplate.afterPropertiesSet();

    //     redisClient = new RedisClient(redisTemplate);
    // }

    // @Test
    // void test() {
    //     redisClient.set("sample", "sample");
    //     var actual = redisClient.get("sample");
    //     var expected = "sample";

    //     assertThat(actual).isEqualTo(expected);
    // }
}
