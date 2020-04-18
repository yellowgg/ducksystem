package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.perm.Role;
import cn.yellowgg.ducksystem.mapper.AdminandroleMapper;
import cn.yellowgg.ducksystem.mapper.RoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:26
 */
@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    AdminandroleMapper adminandroleMapper;

    public int deleteByPrimaryKey(Long id) {
        // 角色有人不能删
        return adminandroleMapper.countByRoleId(id) > UtilConstants.Number.ZERO
                ? UtilConstants.Number.ZERO
                : roleMapper.deleteByPrimaryKey(id);
    }


    public int insert(Role record) {
        return roleMapper.insert(record);
    }


    public int insertOrUpdate(Role record) {
        return roleMapper.insertOrUpdate(record);
    }


    public int insertOrUpdateSelective(Role record) {
        return roleMapper.insertOrUpdateSelective(record);
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
}
