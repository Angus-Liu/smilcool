package com.smilcool.server.base.config.shiro.realm;

import cn.hutool.crypto.SecureUtil;
import com.smilcool.server.core.pojo.po.User;
import com.smilcool.server.core.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * 设置自定义 Realm
 */
@Slf4j
@AllArgsConstructor
@Component("authorizer")
public class CustomAuthorizingRealm extends AuthorizingRealm {

    private final UserService userService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // PrimaryPrincipal 为 userId
        Integer id = (Integer) principals.getPrimaryPrincipal();
        log.info("获取用户 {} 授权信息", id);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取角色信息
        authorizationInfo.setRoles(userService.getRoleNames(id));
        // 获取权限信息
        authorizationInfo.setStringPermissions(userService.getPermissions(id));
        return authorizationInfo;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = String.valueOf(upToken.getPassword());
        User user = userService.getUser(username, SecureUtil.md5(password));
        if (user == null) {
            throw new AccountException("用户名或密码错误");
        }
        // 为减少连表操作，SimpleAuthenticationInfo 的 principal 由 username 改为 userId
        return new SimpleAuthenticationInfo(user.getId(), password, getName());
    }
}
