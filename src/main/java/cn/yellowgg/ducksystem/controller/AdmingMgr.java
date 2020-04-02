package cn.yellowgg.ducksystem.controller;

import cn.hutool.json.JSONObject;
import cn.yellowgg.ducksystem.entity.perm.Administrator;
import cn.yellowgg.ducksystem.service.AdministratorService;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @Description: 后台管理
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 17:05
 */
@Validated
@Controller
@RequestMapping("/admin")
@Api(tags = "后台管理控制器")
public class AdmingMgr {

    @Autowired
    AdministratorService adminService;

    //region 页面
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
    //endregion

    //region 接口
    @GetMapping("/initJson")
    public @ResponseBody
    String getInitMenuJson(@NotNull Long adminId) {
        JSONObject initJson = adminService.getInitJson(adminId);
        return Objects.nonNull(initJson) ? ServiceResult.asSuccess(initJson).toJson() : ServiceResult.asFail("该用户无任何权限").toJson();
    }

    @PostMapping("/updateInfo")
    public @ResponseBody
    String updateInfo(@Valid Administrator admin) {
        if (Objects.isNull(admin.getId())) {
            return ServiceResult.asFail("想浑水摸鱼，爬").toJson();
        }
        adminService.updateRealNameAndEmailById(admin);
        // TODO yellowgg 修改成功之后没有刷新
        SecurityUtils.getSubject().releaseRunAs();
        return ServiceResult.asSuccess(null, "修改成功").toJson();
    }
    //endregion

}
