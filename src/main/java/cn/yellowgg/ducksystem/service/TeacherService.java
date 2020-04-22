package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.Teacher;
import cn.yellowgg.ducksystem.entity.result.TeacherResult;
import cn.yellowgg.ducksystem.mapper.TeacherMapper;
import cn.yellowgg.ducksystem.mapper.TeacherAndCourseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/4/11 21:11
 */
@Service
public class TeacherService {

    @Resource
    private TeacherMapper teacherMapper;
    @Autowired
    TeacherAndCourseMapper teacherandcourseMapper;

    /**
     * 删除老师之前先看有没有在教课
     */
    public int deleteByPrimaryKey(Long id) {
        return teacherandcourseMapper.countByTeacherId(id) > UtilConstants.Number.ZERO
                ? UtilConstants.Number.ZERO
                : teacherMapper.deleteByPrimaryKey(id);
    }


    public int insert(Teacher record) {
        return teacherMapper.insert(record);
    }


    public int insertOrUpdate(Teacher record) {
        return teacherMapper.insertOrUpdate(record);
    }


    public int insertOrUpdateSelective(Teacher record) {
        return teacherMapper.insertOrUpdateSelective(record);
    }


    public int insertSelective(Teacher record) {
        return teacherMapper.insertSelective(record);
    }


    public Teacher selectByPrimaryKey(Long id) {
        return teacherMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(Teacher record) {
        return teacherMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Teacher record) {
        return teacherMapper.updateByPrimaryKey(record);
    }


    public int updateBatch(List<Teacher> list) {
        return teacherMapper.updateBatch(list);
    }


    public int updateBatchSelective(List<Teacher> list) {
        return teacherMapper.updateBatchSelective(list);
    }


    public int batchInsert(List<Teacher> list) {
        return teacherMapper.batchInsert(list);
    }

    public PageInfo<Teacher> queryByAllOrderByIdwithPage(int page, int pageSize, Teacher teacher) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(teacherMapper.queryByAllOrderById(teacher));
    }

    public List<Teacher> queryAll() {
        return teacherMapper.queryByAllOrderById(null);
    }

    public List<TeacherResult> findIdAndName() {
        return teacherMapper.findIdAndName();
    }
}
