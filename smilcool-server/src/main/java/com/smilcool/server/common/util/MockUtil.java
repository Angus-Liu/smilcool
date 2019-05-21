package com.smilcool.server.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 模拟工具类
 *
 * @author Angus
 * @date 2019/5/12
 */
@Slf4j
public class MockUtil {

    private static Integer MOCK_USER_ID = 1;

    /**
     * 模拟获取当前登录用户
     * TODO 2019/5/12 改为获取登录用户
     *
     * @return
     */
    public static Integer currentUserId() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            Integer userId = (Integer) currentUser.getPrincipal();
            log.info("已登录，userId: {}", userId);
            return userId;
        }
        log.info("未登录，mock user id: {}", MOCK_USER_ID);
        return MOCK_USER_ID;
    }

}
