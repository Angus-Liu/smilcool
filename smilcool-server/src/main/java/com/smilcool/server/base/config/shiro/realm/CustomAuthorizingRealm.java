package com.smilcool.server.base.config.shiro.realm;

import com.smilcool.server.core.pojo.po.User;
import com.smilcool.server.core.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Angus
 * @date 2019/4/5
 */
public class CustomAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Integer id = (Integer) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取角色信息
        authorizationInfo.setRoles(userService.getRoles(id));
        // 获取权限信息
        authorizationInfo.setStringPermissions(userService.getPermissions(id));
        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = String.valueOf(upToken.getPassword());
        User user = userService.get(username, password);
        if (user == null) {
            throw new AccountException("用户名或密码错误");
        }
        // 为减少连表操作，SimpleAuthenticationInfo 的 principal 由 username 改为 userId
        return new SimpleAuthenticationInfo(user.getId(), password, getName());
    }
}
