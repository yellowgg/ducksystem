package cn.yellowgg.ducksystem.realm;

import cn.yellowgg.ducksystem.entity.perm.Administrator;
import cn.yellowgg.ducksystem.service.AdministratorService;
import cn.yellowgg.ducksystem.utils.LogUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 10:58
 */
public class MyRealm extends AuthorizingRealm {

    private static final transient Logger log = LogUtils.getPlatformLogger();

    @Autowired
    AdministratorService adminService;

    /**
     * 身份验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("----------身份验证：doGetAuthenticationInfo方法被调用----------");
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        Administrator admin = adminService.findByUserName(upToken.getUsername());
        if (Objects.isNull(admin)) {
            throw new AuthenticationException("用户不存在");
        }
        List<Object> principals = Lists.newArrayList();
        principals.add(admin.getUserName());
        principals.add(admin);
        //身份验证通过,返回一个身份信息
        return new SimpleAuthenticationInfo(principals, admin.getPassword(), getName());
    }

    /**
     * 获取身份信息
     * 我们可以在这个方法中，从数据库获取该用户的权限和角色信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("----------身份权限：doGetAuthorizationInfo方法被调用----------");
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
     * 修改权限之后清理
     */
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

}
