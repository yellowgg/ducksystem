package cn.yellowgg.ducksystem.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

/**
 * @Description: 默认路径的路由设置
 * @Author: yellowgg
 * @Date: Created in 2020/3/30 13:51
 */
@Controller
public class PageController {

    @GetMapping("/")
    public String defaultPath() {
        Subject admin = SecurityUtils.getSubject();
        return Objects.nonNull(admin) && admin.isAuthenticated() ? "redirect:admin/index" : "login";
    }
}
