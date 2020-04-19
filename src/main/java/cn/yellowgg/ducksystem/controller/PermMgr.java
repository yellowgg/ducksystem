package cn.yellowgg.ducksystem.controller;

import cn.hutool.json.JSONObject;
import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.service.PermissionService;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import cn.yellowgg.ducksystem.utils.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/4/19 12:55
 */
@RestController
@RequestMapping("/perm")
public class PermMgr {

    @Autowired
    PermissionService permissionService;

    @GetMapping("/permTree/{roleId}")
    public ServiceResult permTree(@PathVariable String roleId) {
        long id = Long.parseLong(Base64Utils.decodeStrofCount(roleId, UtilConstants.Number.THREE));
        JSONObject initJson = UtilConstants.Number.ZERO == id
                ? permissionService.getAllPermJsonTree()
                : permissionService.getAllPermJsonTreeByRoleId(id);
        return Objects.nonNull(initJson)
                ? ServiceResult.asSuccess(initJson)
                : ServiceResult.asFail("该用户无任何菜单项");
    }
}
