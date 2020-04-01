package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.entity.perm.Role;
import cn.yellowgg.ducksystem.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * @Description:  
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:26
 */
@Service
public class RoleService{

    @Resource
    private RoleMapper roleMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
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

}
