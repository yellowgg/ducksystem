package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/15 19:43
 */
public interface CourseMapper {


    //region 增
    int insert(Course record);

    int insertOrUpdate(Course record);

    int insertOrUpdateSelective(Course record);

    int insertSelective(Course record);

    int batchInsert(@Param("list") List<Course> list);
    //endregion

    //region 删
    int deleteByPrimaryKey(Long id);
    //endregion

    //region 改
    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    int updateBatch(List<Course> list);

    int updateBatchSelective(List<Course> list);
    //endregion

    //region 查
    Course selectByPrimaryKey(Long id);

    List<Course> findAllByIdIn(@Param("idCollection") Collection<Long> idCollection);

    List<Course> queryByAllSelectiveOrderById(Course course);

    List<Course> findThreeIsHot();

    Long countByIsHotCourse();
    //endregion
}