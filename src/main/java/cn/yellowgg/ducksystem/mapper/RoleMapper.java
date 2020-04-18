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


    //region 增
    int insert(Role record);

    int insertOrUpdate(Role record);

    int insertOrUpdateSelective(Role record);

    int insertSelective(Role record);

    int batchInsert(@Param("list") List<Role> list);
    //endregion

    //region 删
    int deleteByPrimaryKey(Long id);
    //endregion

    //region 改
    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int updateBatch(List<Role> list);

    int updateBatchSelective(List<Role> list);
    //endregion

    //region 查
    Role selectByPrimaryKey(Long id);

    List<Role> findAllByIdIn(@Param("idCollection") Collection<Long> idCollection);

    List<Role> findByName(@Param("name") String name);
    //endregion


}