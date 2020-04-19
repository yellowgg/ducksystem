package cn.yellowgg.ducksystem.controller;

import cn.hutool.core.util.StrUtil;
import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.perm.Role;
import cn.yellowgg.ducksystem.service.RoleService;
import cn.yellowgg.ducksystem.service.base.ServiceQueryResult;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import cn.yellowgg.ducksystem.utils.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/4/18 22:04
 */
@Controller
@RequestMapping("/role")
public class RoleMgr {

    @Autowired
    RoleService roleService;

    @GetMapping("/page")
    public String page() {
        return "roleList";
    }

    /**
     * @param permIds 1,2,3 格式
     */
    @PostMapping("/addOrUp")
    @ResponseBody
    public ServiceResult insertOrUpdateSelective(@Valid Role role, String permIds) {
        Set<Long> permIdsSet = null;
        if (StrUtil.isNotBlank(permIds)) {
            permIdsSet = Arrays.stream(permIds.split(StrUtil.COMMA))
                    .map(s -> Long.parseLong(s.trim()))
                    .collect(Collectors.toSet());
        }
        return roleService.insertOrUpdateSelective(role, permIdsSet) >= UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(null, "操作成功")
                : ServiceResult.asFail("操作失败");
    }

    @GetMapping("/data")
    @ResponseBody
    public ServiceQueryResult getData(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      String name) {
        return ServiceQueryResult.asSuccess(roleService.findByNamewithPage(pageNum, pageSize, name));
    }

    @PostMapping("/del/{id}")
    @ResponseBody
    public ServiceResult del(@PathVariable String id) {
        return roleService.deleteByPrimaryKey(Long.parseLong(Base64Utils.decodeStrofCount(id,
                UtilConstants.Number.THREE))) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(null, "删除成功")
                : ServiceResult.asFail("删除失败，请检查此角色是否有管理员");
    }
}
