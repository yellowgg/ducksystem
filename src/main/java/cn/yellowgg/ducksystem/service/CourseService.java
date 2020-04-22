package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.Course;
import cn.yellowgg.ducksystem.entity.expand.CourseExpand;
import cn.yellowgg.ducksystem.entity.association.TeacherAndCourse;
import cn.yellowgg.ducksystem.entity.result.CourseResult;
import cn.yellowgg.ducksystem.mapper.CourseMapper;
import cn.yellowgg.ducksystem.mapper.CourseVideoInfoMapper;
import cn.yellowgg.ducksystem.mapper.TeacherAndCourseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/15 15:38
 */
@Service
public class CourseService {

    @Resource
    private CourseMapper courseMapper;
    @Resource
    private CourseVideoInfoMapper courseVideoInfoMapper;
    @Resource
    private TeacherAndCourseMapper teacherAndCourseMapper;

    public PageInfo<CourseExpand> queryByAllSelectiveOrderByIdwithPage(int page, int pageSize, CourseExpand courseExpand) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(courseMapper.queryByAllSelectiveOrderById(courseExpand));
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

    public int insertOrUpdateSelective(CourseExpand courseExpand) {
        // 如果是修改的话，先改下视频里绑定的名字
        if (Objects.nonNull(courseExpand.getId())) {
            courseVideoInfoMapper.updateCourseNameByCourseId(courseExpand.getName(), courseExpand.getId());
        }
        // 教课表
        teacherAndCourseMapper.insertOrUpdate(new TeacherAndCourse(courseExpand.getTId(), courseExpand.getId()));
        return courseMapper.insertOrUpdateSelective(courseExpand);
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

    public List<CourseExpand> queryAll() {
        return courseMapper.queryByAllSelectiveOrderById(null);
    }

    public List<Course> findThreeIsHot() {
        return courseMapper.countByIsHotCourse() < UtilConstants.Number.THREE
                ? Lists.newArrayList()
                : courseMapper.findThreeIsHot();
    }

    public List<Course> findAllByIdIn(Collection<Long> idCollection) {
        if (CollectionUtils.isEmpty(idCollection)) {
            return Lists.newArrayList();
        }
        List<Course> resultList = courseMapper.findAllByIdIn(idCollection);
        return CollectionUtils.isEmpty(resultList) ? Lists.newArrayList() : resultList;
    }

    public List<CourseResult> findIdAndName() {
        return courseMapper.findIdAndName();
    }
}

