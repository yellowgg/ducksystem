package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.entity.association.AccountAndCourse;
import cn.yellowgg.ducksystem.entity.expand.AccountAndCourseExpand;
import cn.yellowgg.ducksystem.mapper.AccountAndCourseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/23 02:52
 */
@Service
public class AccountAndCourseService {

    @Resource
    private AccountAndCourseMapper accountAndCourseMapper;


    public int deleteByPrimaryKey(Long id) {
        return accountAndCourseMapper.deleteByPrimaryKey(id);
    }


    public int insert(AccountAndCourse record) {
        return accountAndCourseMapper.insert(record);
    }


    public int insertOrUpdate(AccountAndCourse record) {
        return accountAndCourseMapper.insertOrUpdate(record);
    }


    public int insertOrUpdateSelective(AccountAndCourse record) {
        return accountAndCourseMapper.insertOrUpdateSelective(record);
    }


    public int insertSelective(AccountAndCourse record) {
        return accountAndCourseMapper.insertSelective(record);
    }


    public AccountAndCourse selectByPrimaryKey(Long id) {
        return accountAndCourseMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(AccountAndCourse record) {
        return accountAndCourseMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(AccountAndCourse record) {
        return accountAndCourseMapper.updateByPrimaryKey(record);
    }


    public int updateBatch(List<AccountAndCourse> list) {
        return accountAndCourseMapper.updateBatch(list);
    }


    public int updateBatchSelective(List<AccountAndCourse> list) {
        return accountAndCourseMapper.updateBatchSelective(list);
    }


    public int batchInsert(List<AccountAndCourse> list) {
        return accountAndCourseMapper.batchInsert(list);
    }

    public AccountAndCourse findByAccountIdAndCourseId(Long accountId, Long courseId) {
        return accountAndCourseMapper.findByAccountIdAndCourseId(accountId, courseId);
    }

    public List<Long> findCourseIdByAccountId(Long accountId) {
        List<Long> resultList = accountAndCourseMapper.findCourseIdByAccountId(accountId);
        return CollectionUtils.isEmpty(resultList) ? Lists.newArrayList() : resultList;
    }

    public PageInfo<AccountAndCourseExpand> findAllByNickNamewithPage(int page, int pageSize, String nickName) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(accountAndCourseMapper.findAllByNickName(nickName));
    }
}
