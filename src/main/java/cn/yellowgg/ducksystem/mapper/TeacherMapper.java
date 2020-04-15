package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/11 21:11
 */
public interface TeacherMapper {
    //region 增
    int insertOrUpdateSelective(Teacher record);

    int insertSelective(Teacher record);

    int insert(Teacher record);

    int insertOrUpdate(Teacher record);

    int batchInsert(@Param("list") List<Teacher> list);
    //endregion

    //region 删
    int deleteByPrimaryKey(Long id);
    //endregion

    //region 查
    Teacher selectByPrimaryKey(Long id);

    List<Teacher> queryByAllOrderById(Teacher teacher);
    //endregion

    //region 改
    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    int updateBatch(List<Teacher> list);

    int updateBatchSelective(List<Teacher> list);
    //endregion
}