package com.smilcool.server.core.service;

/**
 * @author Angus
 * @date 2019/4/26
 */
public interface TestService {
    Object getCache(String key);

    String updateCache(String key);
}
