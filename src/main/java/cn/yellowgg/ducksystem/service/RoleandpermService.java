package cn.yellowgg.ducksystem.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.yellowgg.ducksystem.entity.association.RoleAndPerm;
import java.util.List;
import cn.yellowgg.ducksystem.mapper.RoleandpermMapper;
/**
 * @Description:  
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:29
 */
@Service
public class RoleandpermService{

    @Resource
    private RoleandpermMapper roleandpermMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return roleandpermMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(RoleAndPerm record) {
        return roleandpermMapper.insert(record);
    }

    
    public int insertOrUpdate(RoleAndPerm record) {
        return roleandpermMapper.insertOrUpdate(record);
    }

    
    public int insertOrUpdateSelective(RoleAndPerm record) {
        return roleandpermMapper.insertOrUpdateSelective(record);
    }

    
    public int insertSelective(RoleAndPerm record) {
        return roleandpermMapper.insertSelective(record);
    }

    
    public RoleAndPerm selectByPrimaryKey(Long id) {
        return roleandpermMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(RoleAndPerm record) {
        return roleandpermMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(RoleAndPerm record) {
        return roleandpermMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatch(List<RoleAndPerm> list) {
        return roleandpermMapper.updateBatch(list);
    }

    
    public int updateBatchSelective(List<RoleAndPerm> list) {
        return roleandpermMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<RoleAndPerm> list) {
        return roleandpermMapper.batchInsert(list);
    }

}
