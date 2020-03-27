package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.association.RoleAndPerm;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:  
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:29
 */
@Mapper
public interface RoleandpermMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleAndPerm record);

    int insertOrUpdate(RoleAndPerm record);

    int insertOrUpdateSelective(RoleAndPerm record);

    int insertSelective(RoleAndPerm record);

    RoleAndPerm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleAndPerm record);

    int updateByPrimaryKey(RoleAndPerm record);

    int updateBatch(List<RoleAndPerm> list);

    int updateBatchSelective(List<RoleAndPerm> list);

    int batchInsert(@Param("list") List<RoleAndPerm> list);
}