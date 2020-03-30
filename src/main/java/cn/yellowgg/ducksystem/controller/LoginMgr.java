package cn.yellowgg.ducksystem.controller;

import cn.yellowgg.ducksystem.service.base.ServiceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/30 10:46
 */
@Controller
@RequestMapping("/login")
@Api(tags = "登录控制器")
public class LoginMgr {

    @GetMapping("/page")
    public String login() {
        return "login";
    }


    @PostMapping(path = "/logging", produces = "text/plain;charset=UTF-8")
    @ApiOperation(value = "登录")
    public @ResponseBody
    String logging(String userName, String password) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken upToken = new UsernamePasswordToken(userName, password);
        upToken.setRememberMe(true);
        try {
            currentUser.login(upToken);
        } catch (Exception e) {
            return ServiceResult.asFail("用户名或密码错误").toJson();
        }
        return ServiceResult.asSuccess("/admin/index").toJson();
    }
}
