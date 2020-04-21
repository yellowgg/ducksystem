package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.Course;
import cn.yellowgg.ducksystem.mapper.CourseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/15 15:38
 */
@Service
public class CourseService {

    @Resource
    private CourseMapper courseMapper;

    public PageInfo<Course> queryByAllSelectiveOrderByIdwithPage(int page, int pageSize, Course course) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(courseMapper.queryByAllSelectiveOrderById(course));
    }

    public int deleteByPrimaryKey(Long id) {
        return courseMapper.deleteByPrimaryKey(id);
    }

    public int insert(Course record) {
        return courseMapper.insert(record);
    }

    public int insertOrUpdate(Course record) {
        return courseMapper.insertOrUpdate(record);
    }

    public int insertOrUpdateSelective(Course record) {
        return courseMapper.insertOrUpdateSelective(record);
    }

    public int insertSelective(Course record) {
        return courseMapper.insertSelective(record);
    }

    public Course selectByPrimaryKey(Long id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Course record) {
        return courseMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Course record) {
        return courseMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<Course> list) {
        return courseMapper.updateBatch(list);
    }

    public int updateBatchSelective(List<Course> list) {
        return courseMapper.updateBatchSelective(list);
    }

    public int batchInsert(List<Course> list) {
        return courseMapper.batchInsert(list);
    }

    public List<Course> queryAll() {
        return courseMapper.queryByAllSelectiveOrderById(null);
    }

    public List<Course> findThreeIsHot() {
        return courseMapper.countByIsHotCourse() < UtilConstants.Number.THREE
                ? Lists.newArrayList()
                : courseMapper.findThreeIsHot();
    }

}

