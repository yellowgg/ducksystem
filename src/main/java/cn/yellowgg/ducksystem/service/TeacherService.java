package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.entity.Teacher;
import cn.yellowgg.ducksystem.mapper.TeacherMapper;
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


    public int deleteByPrimaryKey(Long id) {
        return teacherMapper.deleteByPrimaryKey(id);
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

}
