package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.perm.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:28
 */
@Mapper
public interface PermissionMapper {

    //region 增
    int insert(Permission record);

    int insertOrUpdate(Permission record);

    int insertOrUpdateSelective(Permission record);

    int insertSelective(Permission record);

    int batchInsert(@Param("list") List<Permission> list);
    //endregion

    //region 删
    int deleteByPrimaryKey(Long id);
    //endregion

    //region 改
    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    int updateBatch(List<Permission> list);

    int updateBatchSelective(List<Permission> list);

    //endregion

    //region 查
    Permission selectByPrimaryKey(Long id);

    List<Permission> findAllByIdIn(@Param("idCollection") Collection<Long> idCollection);


    List<Permission> findAllByIdInAndTypeInOrderByOrderNum(@Param("idCollection") Collection<Long> idCollection,
                                                           @Param("typeCollection") Collection<Integer> typeCollection);
    //endregion
}