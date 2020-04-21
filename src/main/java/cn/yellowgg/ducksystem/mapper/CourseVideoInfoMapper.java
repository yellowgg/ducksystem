package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.CourseVideoInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/21 17:17
 */
public interface CourseVideoInfoMapper {

    //region 删
    int deleteByPrimaryKey(Long id);
    //endregion

    //region 改
    int updateByPrimaryKeySelective(CourseVideoInfo record);

    int updateByPrimaryKey(CourseVideoInfo record);

    int updateBatch(List<CourseVideoInfo> list);

    int updateBatchSelective(List<CourseVideoInfo> list);

    int updateCourseNameByCourseId(@Param("updatedCourseName") String updatedCourseName, @Param("courseId") Long courseId);
    //endregion

    //region 增
    int insert(CourseVideoInfo record);

    int insertOrUpdate(CourseVideoInfo record);

    int insertOrUpdateSelective(CourseVideoInfo record);

    int insertSelective(CourseVideoInfo record);

    int batchInsert(@Param("list") List<CourseVideoInfo> list);
    //endregion

    //region 查
    List<CourseVideoInfo> selectAllByCourseId(@Param("courseId") Long courseId);

    CourseVideoInfo selectByPrimaryKey(Long id);
    //endregion
}