package cn.yellowgg.ducksystem.controller;

import cn.yellowgg.ducksystem.service.AdministratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public String login(String userName, String password) {
        System.out.println(userName);
        System.out.println(password);
        return "index";
    }

}
