package cn.yellowgg.ducksystem.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.yellowgg.ducksystem.annotation.Base64DecodeStr;
import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.perm.Administrator;
import cn.yellowgg.ducksystem.entity.perm.Role;
import cn.yellowgg.ducksystem.service.AdministratorService;
import cn.yellowgg.ducksystem.service.PermissionService;
import cn.yellowgg.ducksystem.service.RoleService;
import cn.yellowgg.ducksystem.service.base.ServiceQueryResult;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import cn.yellowgg.ducksystem.utils.Base64Utils;
import cn.yellowgg.ducksystem.utils.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: 后台管理
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 17:05
 */
@Validated
@Controller
@RequestMapping("/admin")
@Api(tags = "后台管理控制器")
public class AdminMgr {

    @Autowired
    AdministratorService adminService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    RoleService roleService;

    //region 页面
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/page")
    public String page(Model model) {
        Map<Long, String> roleIdAndRoleNameMap = roleService.findAll()
                .stream().collect(Collectors.toMap(Role::getId, Role::getName));
        model.addAttribute("roleCheckMap", roleIdAndRoleNameMap);
        return "adminList";
    }
    //endregion

    //region 接口
    @GetMapping("/initJson/{adminId}")
    @ResponseBody
    public ServiceResult getInitMenuJson(@PathVariable String adminId) {
        JSONObject initJson = permissionService.getDirAndMenuPermJsonTreeByAdminId(Long.parseLong(
                Base64Utils.decodeStrofCount(adminId, UtilConstants.Number.THREE)));
        return Objects.nonNull(initJson)
                ? ServiceResult.asSuccess(initJson)
                : ServiceResult.asFail("该用户无任何菜单项");
    }

    @PostMapping("/updateInfo")
    @Base64DecodeStr(excludeField = {"realName"})
    @ResponseBody
    public ServiceResult updateInfo(@Valid Administrator admin) {
        if (Objects.isNull(admin.getId())) {
            return ServiceResult.asFail("想浑水摸鱼，爬");
        }
        adminService.updateRealNameAndEmailById(admin);
        ShiroUtils.setUser(admin);
        return ServiceResult.asSuccess(null, "修改成功");
    }

    @PostMapping("/updatePwd")
    @ResponseBody
    public ServiceResult updatePwd(@NotBlank(message = "输个新密码好吗") String newPwd,
                                   @NotBlank(message = "有内鬼，停止交易") String oldPwd,
                                   @NotBlank(message = "你想改谁") @Base64DecodeStr String adminId,
                                   String userName) {
        Administrator admin = new Administrator(ShiroUtils.createMD5Pwd(oldPwd,
                UtilConstants.Number.THREE), Long.parseLong(adminId));
        // 小程序端只拥有用户名字段，所以这里配合小程序端而修改下，web端是不会传用户名的
        if (StrUtil.isNotBlank(userName)) {
            admin.setUserName(userName);
            admin.setId(null);
        }
        if (Objects.isNull(adminService.findByIdAndPasswordAndUserName(admin))) {
            return ServiceResult.asFail("原密码不正确");
        }
        admin.setPassword(ShiroUtils.createMD5Pwd(newPwd, 3));
        return adminService.updatePasswordById(admin) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(null, "修改成功")
                : ServiceResult.asFail("修改失败");
    }

    @GetMapping("/data")
    @ResponseBody
    public ServiceQueryResult getData(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      Administrator admin) {
        return ServiceQueryResult.asSuccess(adminService.findByAllSelectivewithPage(pageNum, pageSize, admin));
    }

    /**
     * @param roleIds 1,2,3 格式
     */
    @PostMapping("/addOrUp")
    @ResponseBody
    public ServiceResult insertOrUpdateSelective(@Valid Administrator admin, String roleIds) {
        Set<Long> releIdsSet = null;
        if (StrUtil.isNotBlank(roleIds)) {
            releIdsSet = Arrays.stream(roleIds.split(StrUtil.COMMA))
                    .map(s -> Long.parseLong(s.trim()))
                    .collect(Collectors.toSet());
        }
        return adminService.insertOrUpdateSelective(admin, releIdsSet) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(null, "操作成功")
                : ServiceResult.asFail("操作失败");
    }

    @PostMapping("/del/{id}")
    @ResponseBody
    public ServiceResult del(@PathVariable String id) {
        return adminService.deleteByPrimaryKey(Long.parseLong(Base64Utils.decodeStrofCount(id,
                UtilConstants.Number.THREE))) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(null, "删除成功")
                : ServiceResult.asFail("删除失败");
    }
    //endregion

}
