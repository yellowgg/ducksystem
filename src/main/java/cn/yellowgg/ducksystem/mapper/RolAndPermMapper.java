package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.association.RoleAndPerm;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:29
 */
public interface RolAndPermMapper {

    //region 增
    int insert(RoleAndPerm record);

    int insertOrUpdate(RoleAndPerm record);

    int insertOrUpdateSelective(RoleAndPerm record);

    int insertSelective(RoleAndPerm record);

    int batchInsert(@Param("list") List<RoleAndPerm> list);
    //endregion

    //region 删
    int deleteByPrimaryKey(Long id);
    //endregion

    //region 改
    int updateByPrimaryKeySelective(RoleAndPerm record);

    int updateByPrimaryKey(RoleAndPerm record);

    int updateBatch(List<RoleAndPerm> list);

    int updateBatchSelective(List<RoleAndPerm> list);
    //endregion

    //region 查
    RoleAndPerm selectByPrimaryKey(Long id);

    List<Long> findPermIdByRoleId(@Param("roleId") Long roleId);

    List<Long> findDistinctPermIdByRoleIdIn(@Param("roleIdCollection") Collection<Long> roleIdCollection);


    //endregion
}