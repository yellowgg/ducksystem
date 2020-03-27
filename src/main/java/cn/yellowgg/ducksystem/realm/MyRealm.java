package cn.yellowgg.ducksystem.realm;

import cn.yellowgg.ducksystem.utils.LogUtils;
import com.google.common.collect.Sets;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;

import java.util.Set;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 10:58
 */
public class MyRealm extends AuthorizingRealm {

    private static final transient Logger log = LogUtils.getPlatformLogger();

    /**
     * 获取身份信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("----------doGetAuthorizationInfo方法被调用----------");
        String username = (String) getAvailablePrincipal(principalCollection);
        //我们可以通过用户名从数据库获取权限/角色信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //权限
        Set<String> perm = Sets.newHashSet();
        perm.add("printer:print");
        perm.add("printer:query");
        info.setStringPermissions(perm);
        //角色
        Set<String> role = Sets.newHashSet();
        role.add("role1");
        info.setRoles(role);

        return info;
    }

    /**
     * 身份验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("----------doGetAuthenticationInfo方法被调用----------");
        //用户名
        String username = (String) authenticationToken.getPrincipal();
        log.info("username:" + username);
        //密码
        String password = new String((char[]) authenticationToken.getCredentials());
        log.info("password:" + password);
        //从数据库获取用户名密码进行匹配，这里为了方面，省略数据库操作
        if (!"admin".equals(username)) {
            throw new UnknownAccountException();
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException();
        }
        //身份验证通过,返回一个身份信息
        AuthenticationInfo aInfo = new SimpleAuthenticationInfo(username, password, getName());

        return aInfo;
    }

    /**
     * 修改权限之后清理
     */
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
