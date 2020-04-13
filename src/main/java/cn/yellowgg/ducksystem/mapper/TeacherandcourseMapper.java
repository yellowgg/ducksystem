package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.association.TeacherAndCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/13 13:46
 */
@Mapper
public interface TeacherandcourseMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(TeacherAndCourse record);

    int insertOrUpdate(TeacherAndCourse record);

    int insertOrUpdateSelective(TeacherAndCourse record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(TeacherAndCourse record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    TeacherAndCourse selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(TeacherAndCourse record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(TeacherAndCourse record);

    int updateBatch(List<TeacherAndCourse> list);

    int updateBatchSelective(List<TeacherAndCourse> list);

    int batchInsert(@Param("list") List<TeacherAndCourse> list);

    Long countByTeacherId(@Param("teacherId") Long teacherId);
}