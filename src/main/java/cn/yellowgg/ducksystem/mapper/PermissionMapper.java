package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.perm.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:28
 */
@Mapper
public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertOrUpdate(Permission record);

    int insertOrUpdateSelective(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    int updateBatch(List<Permission> list);

    int updateBatchSelective(List<Permission> list);

    int batchInsert(@Param("list") List<Permission> list);
}