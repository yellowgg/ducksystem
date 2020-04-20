package cn.yellowgg.ducksystem.service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.perm.Permission;
import cn.yellowgg.ducksystem.enums.PermissionTypeEnum;
import cn.yellowgg.ducksystem.mapper.PermissionMapper;
import cn.yellowgg.ducksystem.mapper.RoleAndPermMapper;
import cn.yellowgg.ducksystem.vo.MenuInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:28
 */
@Service
public class PermissionService {

    @Resource
    PermissionMapper permissionMapper;
    @Resource
    RoleAndPermMapper roleAndPermMapper;
    @Resource
    AdminAndRoleService adminAndRoleService;

    /**
     * 获取指定用户的目录和菜单权限树
     * 后台设置为目录栏(顶上)和菜单栏(侧边)
     *
     * @return 此jsonObj直接toString就可以用, 无菜单时返回null
     */
    public JSONObject getDirAndMenuPermJsonTreeByAdminId(Long adminId) {
        return getPermJsonTree(adminAndRoleService.findDirAndMenuByAdminId(adminId), Sets.newHashSet(), UtilConstants.Bool.FALSE);
    }

    /**
     * 获取指定角色的所有权限树(角色拥有的权限会打上check)
     * 操作角色相关的时候显示用
     */
    public JSONObject getAllPermJsonTreeByRoleId(Long roleId) {
        return getPermJsonTree(permissionMapper.findAll(),
                roleAndPermMapper.findPermIdByRoleId(roleId).stream().collect(Collectors.toSet()),
                UtilConstants.Bool.TRUE);
    }

    /**
     * 获取所有权限树
     * 操作角色相关的时候显示用
     */
    public JSONObject getAllPermJsonTree() {
        return getPermJsonTree(permissionMapper.findAll(), Sets.newHashSet(), UtilConstants.Bool.TRUE);
    }

    /**
     * 构建权限树
     *
     * @param permissionList     目录、菜单、按钮等权限集合
     * @param needToCheckPermIds 需要打标选中的权限ID 没有就传个空的 这里有bug https://fly.layui.com/jie/54448/ 所以目录级和菜单级都不达标，只打按钮级
     * @param isHasButton        权限树是否要构建按钮级别的权限
     */
    private JSONObject getPermJsonTree(List<Permission> permissionList, Set<Long> needToCheckPermIds, boolean isHasButton) {
        if (CollectionUtils.isEmpty(permissionList)) {
            return null;
        }
        List<MenuInfo> dirAll = Lists.newArrayList();
        // 按类型分组
        Map<Integer, List<Permission>> dirsAndMenusMap = permissionList.stream().collect(Collectors.groupingBy(Permission::getType));
        // 从目录开始
        dirsAndMenusMap.get(PermissionTypeEnum.DIRECTORY.getValue()).forEach(dir -> {
            // 常规管理
            MenuInfo dirObj = new MenuInfo(dir.getTitle(), dir.getIcon(), dir.getUrl(), dir.getId(), false, UtilConstants.Bool.TRUE);
            // 常规管理的子菜单
            List<Permission> oneLevelMenus = getChildMenu(dirsAndMenusMap.get(PermissionTypeEnum.MENU.getValue()), dir.getId());
            oneLevelMenus.forEach(oneLevelMenu -> {
                // 教师管理
                MenuInfo info = new MenuInfo(oneLevelMenu.getTitle(), oneLevelMenu.getIcon(), oneLevelMenu.getUrl(), oneLevelMenu.getId(), false, UtilConstants.Bool.TRUE);
                // 获取教师管理的子菜单
                List<Permission> twoLevelMenus = getChildMenu(dirsAndMenusMap.get(PermissionTypeEnum.MENU.getValue()), oneLevelMenu.getId());
                if (isHasButton) {
                    // 再获取教师管理的按钮 放到教师管理子菜单
                    twoLevelMenus.addAll(getChildMenu(dirsAndMenusMap.get(PermissionTypeEnum.BUTTON.getValue()), oneLevelMenu.getId()));
                }
                // 教师管理添加教师列表等子菜单
                twoLevelMenus.forEach(o -> info.addChild(new MenuInfo(o.getTitle(), o.getIcon(), o.getUrl(),
                        o.getId(), needToCheckPermIds.contains(o.getId()), UtilConstants.Bool.FALSE)));
                // 常规管理添加教师管理
                dirObj.addChild(info);
            });
            // 添加常规管理
            dirAll.add(dirObj);
        });
        return CollectionUtils.isEmpty(dirAll) ? null : JSONUtil.createObj().put("menuInfo", JSONUtil.parse(dirAll));
    }

    private List<Permission> getChildMenu(List<Permission> menu, Long parentId) {
        return menu.stream().filter(x -> parentId.equals(x.getParentId()))
                .sorted(Comparator.comparing(Permission::getOrderNum)).collect(Collectors.toList());
    }

    public int deleteByPrimaryKey(Long id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    public int insert(Permission record) {
        return permissionMapper.insert(record);
    }

    public int insertOrUpdate(Permission record) {
        return permissionMapper.insertOrUpdate(record);
    }

    public int insertOrUpdateSelective(Permission record) {
        return permissionMapper.insertOrUpdateSelective(record);
    }

    public int insertSelective(Permission record) {
        return permissionMapper.insertSelective(record);
    }

    public Permission selectByPrimaryKey(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Permission record) {
        return permissionMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Permission record) {
        return permissionMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<Permission> list) {
        return permissionMapper.updateBatch(list);
    }

    public int updateBatchSelective(List<Permission> list) {
        return permissionMapper.updateBatchSelective(list);
    }

    public int batchInsert(List<Permission> list) {
        return permissionMapper.batchInsert(list);
    }

}
