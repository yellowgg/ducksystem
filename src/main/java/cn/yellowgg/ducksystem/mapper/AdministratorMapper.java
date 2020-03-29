package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.perm.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/3/27 17:57
 */
@Mapper
public interface AdministratorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Administrator record);

    int insertOrUpdate(Administrator record);

    int insertOrUpdateSelective(Administrator record);

    int insertSelective(Administrator record);

    Administrator selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Administrator record);

    int updateByPrimaryKey(Administrator record);

    int updateBatch(List<Administrator> list);

    int updateBatchSelective(List<Administrator> list);

    int batchInsert(@Param("list") List<Administrator> list);
}