package sg.edu.nus.iss.app.workshop24.configs;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class redisConfig {

  private Logger logger = Logger.getLogger(redisConfig.class.getName());
  
  @Value("${spring.redis.host}")
  private String redisHost;

  // value redis port from appln.properties
  @Value("${spring.redis.port}")
  private int redisPort;

  @Value("${spring.redis.username}")
  private String redisUsername;

  @Value("${spring.redis.password}")
  private String redisPassword;

  @Bean
  @Scope("singleton")
  public RedisTemplate<String, String> redisTemplate(){
    final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();

    config.setHostName(redisHost);
    config.setPort(redisPort);
    System.out.println(redisUsername);
    System.out.println(redisPassword);

    if(!redisUsername.isEmpty() && !redisPassword.isEmpty()){
      config.setUsername(redisUsername);
      config.setPassword(redisPassword);
    }
    config.setDatabase(0);

    logger.info("Redis connection: host=%s, port=%d, username: %b, password: %b"
					.formatted(redisHost, redisPort, redisUsername.length() > 0, redisPassword.length() > 0));

    final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
    final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config,jedisClient);
    jedisFac.afterPropertiesSet();

    final RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisFac);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());
    return redisTemplate;
    
  }
  
}
