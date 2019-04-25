package com.smilcool.server.base.config.redis;

import com.smilcool.server.SmilcoolServerApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Angus
 * @date 2019/4/25
 */
public class RedisConfigTest extends SmilcoolServerApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testAddCache() {
        String key = "Name";
        String value = "Angus";
        redisTemplate.opsForValue().set(key, value);
        String res = redisTemplate.opsForValue().get(key);
        Assert.assertEquals(res, value);
    }
}