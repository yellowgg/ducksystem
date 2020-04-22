package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.entity.CourseCollection;
import cn.yellowgg.ducksystem.mapper.CourseCollectionMapper;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/22 11:44
 */
@Service
public class CourseCollectionService {

    @Resource
    private CourseCollectionMapper coursecollectionMapper;
    @Resource
    private CourseCollectionMapper courseCollectionMapper;

    public int deleteByPrimaryKey(Long id) {
        return coursecollectionMapper.deleteByPrimaryKey(id);
    }


    public int insert(CourseCollection record) {
        return coursecollectionMapper.insert(record);
    }


    /**
     * @param isCollect 1 取消收藏 0收藏
     */
    public int insertOrUpdate(CourseCollection record, Integer isCollect) {
        return coursecollectionMapper.insertOrUpdate(record, isCollect);
    }


    public int insertOrUpdateSelective(CourseCollection record) {
        return coursecollectionMapper.insertOrUpdateSelective(record);
    }


    public int insertSelective(CourseCollection record) {
        return coursecollectionMapper.insertSelective(record);
    }


    public CourseCollection selectByPrimaryKey(Long id) {
        return coursecollectionMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(CourseCollection record) {
        return coursecollectionMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(CourseCollection record) {
        return coursecollectionMapper.updateByPrimaryKey(record);
    }


    public int updateBatch(List<CourseCollection> list) {
        return coursecollectionMapper.updateBatch(list);
    }


    public int updateBatchSelective(List<CourseCollection> list) {
        return coursecollectionMapper.updateBatchSelective(list);
    }


    public int batchInsert(List<CourseCollection> list) {
        return coursecollectionMapper.batchInsert(list);
    }

    public CourseCollection findByAccountIdAndCourseId(Long accountId, Long courseId) {
        return coursecollectionMapper.findByAccountIdAndCourseId(accountId, courseId);
    }


    public List<Long> findCourseIdByAccountId(Long accountId) {
        List<Long> resultList = coursecollectionMapper.findCourseIdByAccountId(accountId);
        return CollectionUtils.isEmpty(resultList) ? Lists.newArrayList() : resultList;
    }

    public int deleteByAccountIdAndCourseId(Long accountId, Long courseId) {
        return courseCollectionMapper.deleteByAccountIdAndCourseId(accountId, courseId);
    }
}
