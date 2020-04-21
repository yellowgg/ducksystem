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
    int deleteByPrimaryKey(Long id);

    int insert(CourseVideoInfo record);

    int insertOrUpdate(CourseVideoInfo record);

    int insertOrUpdateSelective(CourseVideoInfo record);

    int insertSelective(CourseVideoInfo record);

    CourseVideoInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseVideoInfo record);

    int updateByPrimaryKey(CourseVideoInfo record);

    int updateBatch(List<CourseVideoInfo> list);

    int updateBatchSelective(List<CourseVideoInfo> list);

    int batchInsert(@Param("list") List<CourseVideoInfo> list);

    List<CourseVideoInfo> selectAllByCourseId(@Param("courseId") Long courseId);
}