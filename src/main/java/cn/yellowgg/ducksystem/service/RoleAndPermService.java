package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.entity.association.RoleAndPerm;
import cn.yellowgg.ducksystem.entity.perm.Permission;
import cn.yellowgg.ducksystem.mapper.PermissionMapper;
import cn.yellowgg.ducksystem.mapper.RolAndPermMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:29
 */
@Service
public class RoleAndPermService {

    @Resource
    private RolAndPermMapper rolAndPermMapper;
    @Resource
    private PermissionMapper permMapper;


    public int deleteByPrimaryKey(Long id) {
        return rolAndPermMapper.deleteByPrimaryKey(id);
    }


    public int insert(RoleAndPerm record) {
        return rolAndPermMapper.insert(record);
    }


    public int insertOrUpdate(RoleAndPerm record) {
        return rolAndPermMapper.insertOrUpdate(record);
    }


    public int insertOrUpdateSelective(RoleAndPerm record) {
        return rolAndPermMapper.insertOrUpdateSelective(record);
    }


    public int insertSelective(RoleAndPerm record) {
        return rolAndPermMapper.insertSelective(record);
    }


    public RoleAndPerm selectByPrimaryKey(Long id) {
        return rolAndPermMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(RoleAndPerm record) {
        return rolAndPermMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(RoleAndPerm record) {
        return rolAndPermMapper.updateByPrimaryKey(record);
    }


    public int updateBatch(List<RoleAndPerm> list) {
        return rolAndPermMapper.updateBatch(list);
    }


    public int updateBatchSelective(List<RoleAndPerm> list) {
        return rolAndPermMapper.updateBatchSelective(list);
    }


    public int batchInsert(List<RoleAndPerm> list) {
        return rolAndPermMapper.batchInsert(list);
    }

    /**
     * 根据角色找权限
     * 角色和权限是一对多
     *
     * @param roleId
     */
    public List<Permission> findPermsByRoleId(Long roleId) {
        return permMapper.findAllByIdIn(rolAndPermMapper.findPermIdByRoleId(roleId));
    }

    public List<Long> findPermIdByRoleId(Long roleId) {
        return rolAndPermMapper.findPermIdByRoleId(roleId);
    }


}
