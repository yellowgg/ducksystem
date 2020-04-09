package cn.yellowgg.ducksystem.controller;

import cn.hutool.json.JSONObject;
import cn.yellowgg.ducksystem.annotation.Base64DecodeStr;
import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.perm.Administrator;
import cn.yellowgg.ducksystem.service.AdministratorService;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import cn.yellowgg.ducksystem.utils.Base64Utils;
import cn.yellowgg.ducksystem.utils.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
    @GetMapping("/initJson/{adminId}")
    public @ResponseBody
    String getInitMenuJson(@PathVariable String adminId) {
        JSONObject initJson = adminService.getInitJson(Long.parseLong(
                Base64Utils.decodeStrofCount(adminId, UtilConstants.Number.THREE)));
        return Objects.nonNull(initJson)
                ? ServiceResult.asSuccess(initJson).toJson()
                : ServiceResult.asFail("该用户无任何菜单项").toJson();
    }

    @PostMapping("/updateInfo")
    @Base64DecodeStr(excludeField = {"realName"})
    public @ResponseBody
    String updateInfo(@Valid Administrator admin) {
        if (Objects.isNull(admin.getId())) {
            return ServiceResult.asFail("想浑水摸鱼，爬").toJson();
        }
        adminService.updateRealNameAndEmailById(admin);
        ShiroUtils.setUser(admin);
        return ServiceResult.asSuccess(null, "修改成功").toJson();
    }

    @PostMapping("/updatePwd")
    public @ResponseBody
    String updatePwd(@NotBlank(message = "输个新密码好吗") String newPwd, @NotBlank(message = "有内鬼，停止交易") String oldPwd,
                     @NotNull @Base64DecodeStr String adminId) {
        Administrator admin = new Administrator(ShiroUtils.createMD5Pwd(oldPwd, 3), Long.parseLong(adminId));
        if (Objects.isNull(adminService.findByIdAndPassword(admin))) {
            return ServiceResult.asFail("原密码不正确").toJson();
        }
        admin.setPassword(ShiroUtils.createMD5Pwd(newPwd, 3));
        adminService.updatePasswordById(admin);
        return ServiceResult.asSuccess(null, "修改成功").toJson();
    }
    //endregion

}
