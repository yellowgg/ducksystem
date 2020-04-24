package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.association.AccountAndCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/23 02:52
 */
public interface AccountAndCourseMapper {


    //region 增
    int insert(AccountAndCourse record);

    int insertOrUpdate(AccountAndCourse record);

    int batchInsert(@Param("list") List<AccountAndCourse> list);

    int insertOrUpdateSelective(AccountAndCourse record);

    int insertSelective(AccountAndCourse record);
    //endregion

    //region 删
    int deleteByPrimaryKey(Long id);
    //endregion

    //region 改
    int updateByPrimaryKeySelective(AccountAndCourse record);

    int updateByPrimaryKey(AccountAndCourse record);

    int updateBatch(List<AccountAndCourse> list);

    int updateBatchSelective(List<AccountAndCourse> list);
    //endregion

    //region 查
    AccountAndCourse selectByPrimaryKey(Long id);

    AccountAndCourse findByAccountIdAndCourseId(@Param("accountId") Long accountId, @Param("courseId") Long courseId);

    List<Long> findCourseIdByAccountId(@Param("accountId") Long accountId);

    //endregion

}