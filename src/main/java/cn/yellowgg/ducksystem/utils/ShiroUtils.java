package cn.yellowgg.ducksystem.utils;

import cn.yellowgg.ducksystem.entity.perm.Administrator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/4/2 14:41
 */
public class ShiroUtils {
    /**
     * 切换身份，登录后，动态更改subject的用户属性
     */
    public static void setUser(Administrator admin) {
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(admin, realmName);
        subject.runAs(newPrincipalCollection);
    }

    /**
     * 使用shiro创建MD5密码
     * 其他工具创建的可能有差别
     *
     * @param pwd   密码
     * @param count 次数
     */
    public static String createMD5Pwd(String pwd, int count) {
        return new SimpleHash("MD5", pwd, null, count).toHex();
    }
}
