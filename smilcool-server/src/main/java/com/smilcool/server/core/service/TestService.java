package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

    private String cache;

    /**
     * 模拟耗时运算
     */
    private Object timeConsumingOperation(String key) {
        log.info("缓存未命中，进行耗时计算");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        User user = new User();
        user.setUsername("Angus");
        user.setPassword("123456");
//        cache = "计算结果：" + key + System.currentTimeMillis();
        return user;
    }

    @Cacheable("cache")
    public Object getCache(String key) {
        return timeConsumingOperation(key);
    }

    @CachePut(cacheNames = "cache")
    public String updateCache(String key) {
        log.info("更新缓存");
        cache = "计算结果：" + key;
        return cache;
    }
}
