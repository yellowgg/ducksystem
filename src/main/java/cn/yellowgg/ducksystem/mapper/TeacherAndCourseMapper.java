package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.association.TeacherAndCourse;
import cn.yellowgg.ducksystem.entity.expand.TeacherAndCourseExpand;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/13 13:46
 */
public interface TeacherAndCourseMapper {

    //region 删
    int deleteByPrimaryKey(Long id);
    //endregion

    //region 增
    int insert(TeacherAndCourse record);

    int insertOrUpdate(TeacherAndCourse record);

    int insertOrUpdateSelective(TeacherAndCourse record);

    int insertSelective(TeacherAndCourse record);

    int batchInsert(@Param("list") List<TeacherAndCourse> list);
    //endregion

    //region 改
    int updateByPrimaryKeySelective(TeacherAndCourse record);

    int updateByPrimaryKey(TeacherAndCourse record);

    int updateBatch(List<TeacherAndCourse> list);

    int updateTeacherIdByCourseId(@Param("teacherId") Long teacherId, @Param("courseId") Long courseId);

    int updateBatchSelective(List<TeacherAndCourse> list);
    //endregion

    //region 查
    TeacherAndCourse selectByPrimaryKey(Long id);

    List<TeacherAndCourseExpand> findByAll(TeacherAndCourse teacherAndCourse);

    Long countByTeacherId(@Param("teacherId") Long teacherId);
    //endregion
}