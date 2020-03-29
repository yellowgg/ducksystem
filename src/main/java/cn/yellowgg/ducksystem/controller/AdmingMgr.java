package cn.yellowgg.ducksystem.controller;

import cn.yellowgg.ducksystem.service.AdministratorService;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 后台管理
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 17:05
 */
@Controller
@RequestMapping("/admin")
@Api(tags = "后台管理控制器")
public class AdmingMgr {

    @Autowired
    AdministratorService adminService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }


    @PostMapping(path = "/login", produces = "text/plain;charset=UTF-8")
    @ApiOperation(value = "登录")
    public @ResponseBody
    String login(String userName, String password) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken upToken = new UsernamePasswordToken(userName, password);
            upToken.setRememberMe(true);
            try {
                currentUser.login(upToken);
            } catch (Exception e) {
                return ServiceResult.asFail("用户名或密码错误").toJson();
            }
        }
        return ServiceResult.asSuccess("/admin/index").toJson();
    }

}
