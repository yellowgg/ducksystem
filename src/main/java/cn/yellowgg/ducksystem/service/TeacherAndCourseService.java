package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.entity.association.TeacherAndCourse;
import cn.yellowgg.ducksystem.mapper.TeacherAndCourseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/13 13:46
 */
@Service
public class TeacherAndCourseService {

    @Resource
    private TeacherAndCourseMapper teacherandcourseMapper;
    @Autowired
    private TeacherAndCourseMapper teacherAndCourseMapper;

    public int deleteByPrimaryKey(Long id) {
        return teacherandcourseMapper.deleteByPrimaryKey(id);
    }

    public int insert(TeacherAndCourse record) {
        return teacherandcourseMapper.insert(record);
    }

    public int insertOrUpdate(TeacherAndCourse record) {
        return teacherandcourseMapper.insertOrUpdate(record);
    }

    public int insertOrUpdateSelective(TeacherAndCourse record) {
        return teacherandcourseMapper.insertOrUpdateSelective(record);
    }

    public int insertSelective(TeacherAndCourse record) {
        return teacherandcourseMapper.insertSelective(record);
    }

    public TeacherAndCourse selectByPrimaryKey(Long id) {
        return teacherandcourseMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(TeacherAndCourse record) {
        return teacherandcourseMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TeacherAndCourse record) {
        return teacherandcourseMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<TeacherAndCourse> list) {
        return teacherandcourseMapper.updateBatch(list);
    }

    public int updateBatchSelective(List<TeacherAndCourse> list) {
        return teacherandcourseMapper.updateBatchSelective(list);
    }

    public int batchInsert(List<TeacherAndCourse> list) {
        return teacherandcourseMapper.batchInsert(list);
    }

    public Long countByTeacherId(Long teacherId) {
        return teacherandcourseMapper.countByTeacherId(teacherId);
    }

	public List<TeacherAndCourse> findByAll(TeacherAndCourse teacherAndCourse){
		 return teacherAndCourseMapper.findByAll(teacherAndCourse);
	}


    public PageInfo<TeacherAndCourse> findByAllwithPage(int page, int pageSize, TeacherAndCourse teacherAndCourse) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(teacherAndCourseMapper.findByAll(teacherAndCourse));
    }
}
