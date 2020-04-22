package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.CourseCollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/22 11:44
 */
public interface CourseCollectionMapper {


    //region 增
    int batchInsert(@Param("list") List<CourseCollection> list);

    int insert(CourseCollection record);

    int insertOrUpdate(@Param("record") CourseCollection record, @Param("isCollect") Integer isCollect);

    int insertOrUpdateSelective(CourseCollection record);

    int insertSelective(CourseCollection record);
    //endregion

    //region 删
    int deleteByPrimaryKey(Long id);
    //endregion

    //region 改
    int updateByPrimaryKeySelective(CourseCollection record);

    int updateByPrimaryKey(CourseCollection record);

    int updateBatch(List<CourseCollection> list);

    int updateBatchSelective(List<CourseCollection> list);
    //endregion

    //region 查
    CourseCollection selectByPrimaryKey(Long id);

    List<Long> findCourseIdByAccountId(@Param("accountId") Long accountId);

    CourseCollection findByAccountIdAndCourseId(@Param("accountId") Long accountId, @Param("courseId") Long courseId);
    //endregion
}