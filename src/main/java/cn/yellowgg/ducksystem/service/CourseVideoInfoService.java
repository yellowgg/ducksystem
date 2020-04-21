package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.CourseVideoInfo;
import cn.yellowgg.ducksystem.mapper.CourseVideoInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/21 15:09
 */
@Service
public class CourseVideoInfoService {

    @Resource
    private CourseVideoInfoMapper coursevideoinfoMapper;


    public int deleteByPrimaryKey(Long id) {
        return coursevideoinfoMapper.deleteByPrimaryKey(id);
    }


    public int insert(CourseVideoInfo record) {
        return coursevideoinfoMapper.insert(record);
    }


    public int insertOrUpdate(CourseVideoInfo record) {
        return coursevideoinfoMapper.insertOrUpdate(record);
    }


    public int insertOrUpdateSelective(CourseVideoInfo record) {
        return coursevideoinfoMapper.insertOrUpdateSelective(record);
    }


    public int insertSelective(CourseVideoInfo record) {
        return coursevideoinfoMapper.insertSelective(record);
    }


    public CourseVideoInfo selectByPrimaryKey(Long id) {
        return coursevideoinfoMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(CourseVideoInfo record) {
        return coursevideoinfoMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(CourseVideoInfo record) {
        return coursevideoinfoMapper.updateByPrimaryKey(record);
    }


    public int updateBatch(List<CourseVideoInfo> list) {
        return coursevideoinfoMapper.updateBatch(list);
    }


    public int updateBatchSelective(List<CourseVideoInfo> list) {
        return coursevideoinfoMapper.updateBatchSelective(list);
    }


    public int batchInsert(List<CourseVideoInfo> list) {
        return coursevideoinfoMapper.batchInsert(list);
    }

    public List<CourseVideoInfo> selectAllByCourseId(Long courseId) {
        return coursevideoinfoMapper.selectAllByCourseId(courseId);
    }


    public PageInfo<CourseVideoInfo> selectAllByCourseIdwithPage(int page, int pageSize, Long courseId) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(coursevideoinfoMapper
                .selectAllByCourseId(UtilConstants.Number.ZERO == courseId ? null : courseId));
    }
}

