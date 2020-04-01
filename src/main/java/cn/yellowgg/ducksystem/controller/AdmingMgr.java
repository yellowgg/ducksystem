package cn.yellowgg.ducksystem.controller;

import cn.hutool.json.JSONObject;
import cn.yellowgg.ducksystem.service.AdministratorService;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/initJson")
    public @ResponseBody
    String getInitMenuJson(@NotNull Long adminId) {
        JSONObject initJson = adminService.getInitJson(adminId);
        return Objects.nonNull(initJson) ? ServiceResult.asSuccess(initJson).toJson() : ServiceResult.asFail("该用户无任何权限").toJson();
    }

}
