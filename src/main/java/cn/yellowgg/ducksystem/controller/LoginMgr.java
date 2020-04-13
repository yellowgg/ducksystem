package cn.yellowgg.ducksystem.controller;

import cn.yellowgg.ducksystem.annotation.Base64DecodeStr;
import cn.yellowgg.ducksystem.entity.perm.Administrator;
import cn.yellowgg.ducksystem.service.AdministratorService;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import cn.yellowgg.ducksystem.utils.LogUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotBlank;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/30 10:46
 */
@Validated
@Controller
@RequestMapping("/login")
@Api(tags = "登录控制器")
public class LoginMgr {

    private static final transient Logger log = LogUtils.getPlatformLogger();

    @Autowired
    AdministratorService adminService;

    @GetMapping("/page")
    public String login() {
        return "login";
    }

    @PostMapping(path = "/logging")
    @ApiOperation(value = "登录")
    @ResponseBody
    public ServiceResult logging(@NotBlank(message = "用户名不能为空") @Base64DecodeStr String userName,
                                 @NotBlank(message = "密码不能为空") String password) {
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(userName, password));
        } catch (Exception e) {
            log.info("登录错误", e);
            return ServiceResult.asFail("用户名或密码错误");
        }
        adminService.updateLastLoginTime((Administrator) SecurityUtils.getSubject().getPrincipal());
        return ServiceResult.asSuccess("/admin/index");
    }
}
