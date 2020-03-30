package cn.yellowgg.ducksystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: 默认路径的路由设置
 * @Author: yellowgg
 * @Date: Created in 2020/3/30 13:51
 */
@Controller
public class PageController {

    @GetMapping(value = "/")
    public String defaultPath() {
        return "login";
    }
}
