package cn.yellowgg.ducksystem.service;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.perm.Administrator;
import cn.yellowgg.ducksystem.entity.perm.Permission;
import cn.yellowgg.ducksystem.enums.PermissionTypeEnum;
import cn.yellowgg.ducksystem.mapper.AdministratorMapper;
import cn.yellowgg.ducksystem.vo.MenuInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 14:30
 */
@Service
public class AdministratorService {

    @Resource
    AdministratorMapper administratorMapper;
    @Resource
    AdminAndRoleService adminAndRoleService;


    public int deleteByPrimaryKey(Long id) {
        return administratorMapper.deleteByPrimaryKey(id);
    }


    public int insert(Administrator record) {
        return administratorMapper.insert(record);
    }


    public int insertOrUpdate(Administrator record) {
        return administratorMapper.insertOrUpdate(record);
    }


    public int insertOrUpdateSelective(Administrator record) {
        return administratorMapper.insertOrUpdateSelective(record);
    }


    public int insertSelective(Administrator record) {
        return administratorMapper.insertSelective(record);
    }


    public Administrator selectByPrimaryKey(Long id) {
        return administratorMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(Administrator record) {
        return administratorMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Administrator record) {
        return administratorMapper.updateByPrimaryKey(record);
    }


    public int updateBatch(List<Administrator> list) {
        return administratorMapper.updateBatch(list);
    }


    public int updateBatchSelective(List<Administrator> list) {
        return administratorMapper.updateBatchSelective(list);
    }


    public int batchInsert(List<Administrator> list) {
        return administratorMapper.batchInsert(list);
    }

    public Administrator findByUserName(String userName) {
        return administratorMapper.findByUserName(userName);
    }

    public int updateLastLoginTime(Administrator administrator) {
        return administratorMapper.updateLastLoginTimeByIdAndUserName(
                LocalDateTime.now().withNano(UtilConstants.Number.ZERO),
                administrator.getId(), administrator.getUserName());
    }

    /**
     * 获取用户构建后台的json
     * 后台设置为目录栏(顶上)和菜单栏(侧边)
     *
     * @return 此jsonObj直接toString就可以用, 无菜单时返回null
     */
    public JSONObject getInitJson(Long adminId) {
        List<Permission> dirsAndMenus = adminAndRoleService.findDirAndMenuByAdminId(adminId);
        if (CollectionUtils.isEmpty(dirsAndMenus)) {
            return null;
        }
        List<MenuInfo> dirAll = Lists.newArrayList();
        Map<Integer, List<Permission>> dirsAndMenusMap = dirsAndMenus.stream().collect(Collectors.groupingBy(Permission::getType));
        dirsAndMenusMap.get(PermissionTypeEnum.DIRECTORY.getValue()).forEach(dir -> {
            MenuInfo dirObj = new MenuInfo(dir.getTitle(), dir.getIcon(), dir.getUrl(), true);
            List<Permission> oneLevelMenus = getChildMenu(dirsAndMenusMap.get(PermissionTypeEnum.MENU.getValue()), dir.getId());
            oneLevelMenus.forEach(oneLevelMenu -> {
                MenuInfo info = new MenuInfo(oneLevelMenu.getTitle(), oneLevelMenu.getIcon(), oneLevelMenu.getUrl(), true);
                List<Permission> twoLevelMenus = getChildMenu(dirsAndMenusMap.get(PermissionTypeEnum.MENU.getValue()), oneLevelMenu.getId());
                twoLevelMenus.forEach(o -> info.addChild(new MenuInfo(o.getTitle(), o.getIcon(), o.getUrl(), false)));
                dirObj.addChild(info);
            });
            dirAll.add(dirObj);
        });
        return CollectionUtils.isEmpty(dirAll) ? null : JSONUtil.createObj().put("menuInfo", JSONUtil.parse(dirAll));
    }

    private List<Permission> getChildMenu(List<Permission> menu, Long parentId) {
        return menu.stream().filter(x -> parentId.equals(x.getParentId())).sorted(Comparator.comparing(Permission::getOrderNum))
                .collect(Collectors.toList());
    }

    public int updateRealNameAndEmailById(Administrator admin) {
        return administratorMapper.updateRealNameAndEmailById(admin.getRealName(), admin.getEmail(), admin.getId());
    }


}
