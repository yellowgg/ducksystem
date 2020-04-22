package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.entity.IntegralRecord;
import cn.yellowgg.ducksystem.mapper.IntegralrecordMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * @Description:  
 * @Author: yellowgg
 * @Date: Created in 2020/4/22 00:59
 */
@Service
public class IntegralrecordService{

    @Resource
    private IntegralrecordMapper integralrecordMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return integralrecordMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(IntegralRecord record) {
        return integralrecordMapper.insert(record);
    }

    
    public int insertOrUpdate(IntegralRecord record) {
        return integralrecordMapper.insertOrUpdate(record);
    }

    
    public int insertOrUpdateSelective(IntegralRecord record) {
        return integralrecordMapper.insertOrUpdateSelective(record);
    }

    
    public int insertSelective(IntegralRecord record) {
        return integralrecordMapper.insertSelective(record);
    }

    
    public IntegralRecord selectByPrimaryKey(Long id) {
        return integralrecordMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(IntegralRecord record) {
        return integralrecordMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(IntegralRecord record) {
        return integralrecordMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatch(List<IntegralRecord> list) {
        return integralrecordMapper.updateBatch(list);
    }

    
    public int updateBatchSelective(List<IntegralRecord> list) {
        return integralrecordMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<IntegralRecord> list) {
        return integralrecordMapper.batchInsert(list);
    }

}
