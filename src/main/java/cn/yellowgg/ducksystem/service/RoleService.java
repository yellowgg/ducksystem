package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.perm.Role;
import cn.yellowgg.ducksystem.mapper.AdminandroleMapper;
import cn.yellowgg.ducksystem.mapper.RoleAndPermMapper;
import cn.yellowgg.ducksystem.mapper.RoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:26
 */
@Service
public class RoleService {

    @Resource
    RoleMapper roleMapper;
    @Resource
    RoleAndPermMapper roleAndPermMapper;
    @Resource
    AdminandroleMapper adminandroleMapper;

    @Autowired
    RoleAndPermService roleAndPermService;
    @Autowired
    AdminAndRoleService adminAndRoleService;


    /**
     * 删除角色
     */
    public int deleteByPrimaryKey(Long roleId) {
        return adminandroleMapper.countByRoleId(roleId) > UtilConstants.Number.ZERO
                ? UtilConstants.Number.ZERO
                : roleMapper.deleteByPrimaryKey(roleId);
    }


    public int insert(Role record) {
        return roleMapper.insert(record);
    }


    public int insertOrUpdate(Role record) {
        return roleMapper.insertOrUpdate(record);
    }


    public int insertOrUpdateSelective(Role record, Collection<Long> permIds) {
        Collection<Long> permIdsSet = Optional.ofNullable(permIds).orElseGet(() -> Sets.newHashSet());
        roleMapper.insertOrUpdateSelective(record);
        // 先删除此角色的权限再放回去
        roleAndPermMapper.deleteByRoleId(record.getId());
        return CollectionUtils.isEmpty(permIdsSet)
                ? UtilConstants.Number.ONE
                : roleAndPermMapper.batchInsert(roleAndPermService.getListOfPermIdsAndOneRole(permIdsSet, record.getId()));
    }


    public int insertSelective(Role record) {
        return roleMapper.insertSelective(record);
    }


    public Role selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(Role record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Role record) {
        return roleMapper.updateByPrimaryKey(record);
    }


    public int updateBatch(List<Role> list) {
        return roleMapper.updateBatch(list);
    }


    public int updateBatchSelective(List<Role> list) {
        return roleMapper.updateBatchSelective(list);
    }


    public int batchInsert(List<Role> list) {
        return roleMapper.batchInsert(list);
    }

    public PageInfo<Role> findByNamewithPage(int page, int pageSize, String name) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(roleMapper.findByName(name));
    }

    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    public List<Role> findAllByAdminId(Long id) {
        return adminAndRoleService.findRoleByAdminId(id);
    }
}
