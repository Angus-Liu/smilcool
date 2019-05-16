package com.smilcool.server.base.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * Spring Cache 和 Redis 配置：
 * <p>
 * TODO: 2019/4/24 缓存
 * TODO: 2019/4/24 限流
 *
 * @author Angus
 * @date 2019/4/24
 */
@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 定义 StringRedisTemplate ，指定序列化和反序列化的处理类
     * http://www.importnew.com/29554.html
     * http://blog.didispace.com/springbootredis/
     *
     * @param factory 默认实现为 LettuceConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        // 一般采用 StringRedisTemplate 接口进行操作（默认采用 string 的序列化，保存 key 和 value 时都是通过此序列化策略）
        StringRedisTemplate template = new StringRedisTemplate(factory);
        // 配置序列化工具
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 序列化值时使用此序列化方法
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    // TODO: 2019/4/26 配置自定义 CacheManager
}
