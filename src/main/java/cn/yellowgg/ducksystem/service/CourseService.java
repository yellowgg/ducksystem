package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.Course;
import cn.yellowgg.ducksystem.mapper.CourseMapper;
import cn.yellowgg.ducksystem.mapper.CourseVideoInfoMapper;
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
        // 如果是修改的话，先改下视频里绑定的名字
        if (Objects.nonNull(record.getId())) {
            courseVideoInfoMapper.updateCourseNameByCourseId(record.getName(), record.getId());
        }
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

    public List<Course> findAllByIdIn(Collection<Long> idCollection) {
        if (CollectionUtils.isEmpty(idCollection)) {
            return Lists.newArrayList();
        }
        List<Course> resultList = courseMapper.findAllByIdIn(idCollection);
        return CollectionUtils.isEmpty(resultList) ? Lists.newArrayList() : resultList;
    }

}

