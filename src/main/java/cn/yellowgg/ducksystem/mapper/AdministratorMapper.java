package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.perm.Administrator;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Description: 按增删改查
 * @Author: yellowgg
 * @Date: Created in 2020/3/27 17:57
 */
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

    int updateLastLoginTimeByIdAndUserName(@Param("updatedLastLoginTime") LocalDateTime updatedLastLoginTime,
                                           @Param("id") Long id, @Param("userName") String userName);

    int updateRealNameAndEmailById(@Param("realName") String realName, @Param("email") String email, @Param("id") Long id);

    int updatePasswordByIdOrUserName(Map<String, Object> params);
    //endregion

    //region 查
    Administrator findBySeleceive(Map<String, Object> params);

    //endregion
}