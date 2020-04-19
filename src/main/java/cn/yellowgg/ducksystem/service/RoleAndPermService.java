package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.entity.association.RoleAndPerm;
import cn.yellowgg.ducksystem.entity.perm.Permission;
import cn.yellowgg.ducksystem.enums.PermissionTypeEnum;
import cn.yellowgg.ducksystem.mapper.PermissionMapper;
import cn.yellowgg.ducksystem.mapper.RoleAndPermMapper;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:29
 */
@Service
public class RoleAndPermService {

    @Resource
    RoleAndPermMapper roleAndPermMapper;
    @Resource
    PermissionMapper permMapper;

    public List<RoleAndPerm> getListOfPermIdsAndOneRole(Collection<Long> permIds, Long roleId) {
        List<RoleAndPerm> result = Lists.newArrayList();
        permIds.forEach(x -> result.add(RoleAndPerm.init(roleId, x)));
        return result;
    }

    public int deleteByPrimaryKey(Long id) {
        return roleAndPermMapper.deleteByPrimaryKey(id);
    }


    public int insert(RoleAndPerm record) {
        return roleAndPermMapper.insert(record);
    }


    public int insertOrUpdate(RoleAndPerm record) {
        return roleAndPermMapper.insertOrUpdate(record);
    }


    public int insertOrUpdateSelective(RoleAndPerm record) {
        return roleAndPermMapper.insertOrUpdateSelective(record);
    }


    public int insertSelective(RoleAndPerm record) {
        return roleAndPermMapper.insertSelective(record);
    }


    public RoleAndPerm selectByPrimaryKey(Long id) {
        return roleAndPermMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(RoleAndPerm record) {
        return roleAndPermMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(RoleAndPerm record) {
        return roleAndPermMapper.updateByPrimaryKey(record);
    }


    public int updateBatch(List<RoleAndPerm> list) {
        return roleAndPermMapper.updateBatch(list);
    }


    public int updateBatchSelective(List<RoleAndPerm> list) {
        return roleAndPermMapper.updateBatchSelective(list);
    }


    public int batchInsert(List<RoleAndPerm> list) {
        return roleAndPermMapper.batchInsert(list);
    }

    /**
     * 根据角色找权限
     * 角色和权限是一对多
     *
     * @param roleId
     */
    public List<Permission> findPermsByRoleId(Long roleId) {
        return permMapper.findAllByIdIn(roleAndPermMapper.findPermIdByRoleId(roleId));
    }


    /**
     * 根据多角色找对应按钮权限
     */
    public List<Permission> findButtonByRoleIds(List<Long> roleIds) {
        return permMapper.findAllByIdInAndTypeInOrderByOrderNum(roleAndPermMapper.findDistinctPermIdByRoleIdIn(roleIds),
                Lists.newArrayList(PermissionTypeEnum.BUTTON.getValue()));
    }

    public int deleteByRoleId(Long roleId) {
        return roleAndPermMapper.deleteByRoleId(roleId);
    }
}
