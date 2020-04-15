package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/15 19:43
 */
public interface CourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Course record);

    int insertOrUpdate(Course record);

    int insertOrUpdateSelective(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    int updateBatch(List<Course> list);

    int updateBatchSelective(List<Course> list);

    int batchInsert(@Param("list") List<Course> list);

    List<Course> queryByAllSelectiveOrderById(Course course);
}