package cn.yellowgg.ducksystem.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import cn.yellowgg.ducksystem.entity.association.AdminAndRole;
import cn.yellowgg.ducksystem.mapper.AdminandroleMapper;
/**
 * @Description:  
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:29
 */
@Service
public class AdminandroleService{

    @Resource
    private AdminandroleMapper adminandroleMapper;

    
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

}
