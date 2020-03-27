package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.association.AdminAndRole;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:  
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:29
 */
@Mapper
public interface AdminandroleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminAndRole record);

    int insertOrUpdate(AdminAndRole record);

    int insertOrUpdateSelective(AdminAndRole record);

    int insertSelective(AdminAndRole record);

    AdminAndRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminAndRole record);

    int updateByPrimaryKey(AdminAndRole record);

    int updateBatch(List<AdminAndRole> list);

    int updateBatchSelective(List<AdminAndRole> list);

    int batchInsert(@Param("list") List<AdminAndRole> list);
}