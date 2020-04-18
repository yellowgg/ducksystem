package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.perm.Role;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:26
 */
public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertOrUpdate(Role record);

    int insertOrUpdateSelective(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> findAllByIdIn(@Param("idCollection") Collection<Long> idCollection);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int updateBatch(List<Role> list);

    int updateBatchSelective(List<Role> list);

    int batchInsert(@Param("list") List<Role> list);
}