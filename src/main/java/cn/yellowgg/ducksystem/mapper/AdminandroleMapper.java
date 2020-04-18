package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.association.AdminAndRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 用户-角色
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:29
 */
public interface AdminandroleMapper {

    //region 增
    int insert(AdminAndRole record);

    int insertOrUpdate(AdminAndRole record);

    int insertOrUpdateSelective(AdminAndRole record);

    int insertSelective(AdminAndRole record);

    int batchInsert(@Param("list") List<AdminAndRole> list);
    //endregion

    //region 删
    int deleteByPrimaryKey(Long id);
    //endregion

    //region 改
    int updateByPrimaryKeySelective(AdminAndRole record);

    int updateByPrimaryKey(AdminAndRole record);

    int updateBatch(List<AdminAndRole> list);

    int updateBatchSelective(List<AdminAndRole> list);
    //endregion

    //region 查
    AdminAndRole selectByPrimaryKey(Long id);

    List<Long> findRoleIdByAdminId(@Param("adminId") Long adminId);

    Long countByRoleId(@Param("roleId") Long roleId);

    //endregion
}