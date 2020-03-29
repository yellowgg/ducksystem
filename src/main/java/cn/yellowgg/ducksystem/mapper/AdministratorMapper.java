package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.perm.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 按增删改查
 * @Author: yellowgg
 * @Date: Created in 2020/3/27 17:57
 */
@Mapper
public interface AdministratorMapper {
    //region 增
    int insert(Administrator record);

    int insertOrUpdate(Administrator record);

    int insertOrUpdateSelective(Administrator record);

    int insertSelective(Administrator record);

    int batchInsert(@Param("list") List<Administrator> list);
    //endregion

    //region 删
    int deleteByPrimaryKey(Long id);
    //endregion

    //region 改
    int updateByPrimaryKeySelective(Administrator record);

    int updateByPrimaryKey(Administrator record);

    int updateBatch(List<Administrator> list);

    int updateBatchSelective(List<Administrator> list);
    //endregion

    //region 查
    Administrator selectByPrimaryKey(Long id);

    Administrator findByUserName(@Param("userName") String userName);


    //endregion
}