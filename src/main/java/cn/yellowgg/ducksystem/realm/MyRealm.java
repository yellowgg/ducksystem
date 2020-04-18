package cn.yellowgg.ducksystem.realm;

import cn.yellowgg.ducksystem.entity.perm.Administrator;
import cn.yellowgg.ducksystem.entity.perm.Permission;
import cn.yellowgg.ducksystem.entity.perm.Role;
import cn.yellowgg.ducksystem.service.AdminAndRoleService;
import cn.yellowgg.ducksystem.service.AdministratorService;
import cn.yellowgg.ducksystem.service.RoleAndPermService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description: shiro 自定义Realm
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 10:58
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    AdministratorService adminService;
    @Autowired
    AdminAndRoleService adminandroleService;
    @Autowired
    RoleAndPermService roleandpermService;

    /**
     * 身份验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        Administrator admin = adminService.findByUserName(upToken.getUsername());
        if (Objects.isNull(admin)) {
            throw new AuthenticationException("用户不存在");
        }
        String password = admin.getPassword();
        admin.setPassword(null);
        return new SimpleAuthenticationInfo(admin, password, getName());
    }

    /**
     * 获取身份信息
     * 我们可以在这个方法中，从数据库获取该用户的权限和角色信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Administrator admin = (Administrator) principalCollection.getPrimaryPrincipal();
        List<Role> role = adminandroleService.findRoleByAdminId(admin.getId());
        info.setRoles(role.stream().map(Role::getName).collect(Collectors.toSet()));
        // 这里只设置button级别的权限，因为菜单是用单独的接口来控制显示的
        List<Permission> perms = roleandpermService.findButtonByRoleIds(role.stream().map(Role::getId).collect(Collectors.toList()));
        info.setStringPermissions(perms.stream().map(Permission::getPerms).collect(Collectors.toSet()));
        return info;
    }


    /**
     * 修改权限之后清理缓存
     */
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

}
