package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.entity.association.AdminAndRole;
import cn.yellowgg.ducksystem.entity.perm.Permission;
import cn.yellowgg.ducksystem.entity.perm.Role;
import cn.yellowgg.ducksystem.enums.PermissionTypeEnum;
import cn.yellowgg.ducksystem.mapper.AdminandroleMapper;
import cn.yellowgg.ducksystem.mapper.PermissionMapper;
import cn.yellowgg.ducksystem.mapper.RolAndPermMapper;
import cn.yellowgg.ducksystem.mapper.RoleMapper;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:29
 */
@Service
public class AdminAndRoleService {

    @Resource
    private AdminandroleMapper adminandroleMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolAndPermMapper rolAndPermMapper;
    @Resource
    private PermissionMapper permMapper;


    public int deleteByPrimaryKey(Long id) {
        return adminandroleMapper.deleteByPrimaryKey(id);
    }


    public int insert(AdminAndRole record) {
        return adminandroleMapper.insert(record);
    }


    public int insertOrUpdate(AdminAndRole record) {
        return adminandroleMapper.insertOrUpdate(record);
    }


    public int insertOrUpdateSelective(AdminAndRole record) {
        return adminandroleMapper.insertOrUpdateSelective(record);
    }


    public int insertSelective(AdminAndRole record) {
        return adminandroleMapper.insertSelective(record);
    }


    public AdminAndRole selectByPrimaryKey(Long id) {
        return adminandroleMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(AdminAndRole record) {
        return adminandroleMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(AdminAndRole record) {
        return adminandroleMapper.updateByPrimaryKey(record);
    }


    public int updateBatch(List<AdminAndRole> list) {
        return adminandroleMapper.updateBatch(list);
    }


    public int updateBatchSelective(List<AdminAndRole> list) {
        return adminandroleMapper.updateBatchSelective(list);
    }


    public int batchInsert(List<AdminAndRole> list) {
        return adminandroleMapper.batchInsert(list);
    }


    /**
     * 根据单用户找角色
     *
     * @return
     */
    public List<Role> findRoleByAdminId(Long adminId) {
        return roleMapper.findAllByIdIn(adminandroleMapper.findRoleIdByAdminId(adminId));
    }

    /**
     * 根据单用户找目录和菜单
     */
    public List<Permission> findDirAndMenuByAdminId(Long adminId) {
        List<Long> roleIds = adminandroleMapper.findRoleIdByAdminId(adminId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        List<Long> permIds = rolAndPermMapper.findDistinctPermIdByRoleIdIn(roleIds);
        if (CollectionUtils.isEmpty(permIds)) {
            return Lists.newArrayList();
        }
        return permMapper.findAllByIdInAndTypeInOrderByOrderNum(permIds,
                Lists.newArrayList(PermissionTypeEnum.DIRECTORY.getValue(), PermissionTypeEnum.MENU.getValue()));
    }

}
