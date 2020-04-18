package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.entity.perm.Permission;
import cn.yellowgg.ducksystem.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:28
 */
@Service
public class PermissionService {

    @Resource
    private PermissionMapper permissionMapper;


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
