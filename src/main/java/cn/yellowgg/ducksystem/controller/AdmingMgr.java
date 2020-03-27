package cn.yellowgg.ducksystem.controller;

import cn.yellowgg.ducksystem.entity.perm.Permission;
import cn.yellowgg.ducksystem.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 后台管理
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 17:05
 */
@Controller
public class AdmingMgr {

    @Autowired
    PermissionService permissionService;

    @RequestMapping("/index")
    public String index(Model model) {
        String name = "xiaosha";
        model.addAttribute("name", name);
        return "index";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insertPerm(Permission permission) {
        int insert = permissionService.insert(permission);
        System.out.println(insert);
        return "success";
    }

}
