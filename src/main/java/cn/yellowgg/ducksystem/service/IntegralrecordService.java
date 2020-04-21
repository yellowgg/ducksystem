package cn.yellowgg.ducksystem.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import cn.yellowgg.ducksystem.mapper.IntegralrecordMapper;
import cn.yellowgg.ducksystem.entity.Integralrecord;
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

    
    public int insert(Integralrecord record) {
        return integralrecordMapper.insert(record);
    }

    
    public int insertOrUpdate(Integralrecord record) {
        return integralrecordMapper.insertOrUpdate(record);
    }

    
    public int insertOrUpdateSelective(Integralrecord record) {
        return integralrecordMapper.insertOrUpdateSelective(record);
    }

    
    public int insertSelective(Integralrecord record) {
        return integralrecordMapper.insertSelective(record);
    }

    
    public Integralrecord selectByPrimaryKey(Long id) {
        return integralrecordMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Integralrecord record) {
        return integralrecordMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Integralrecord record) {
        return integralrecordMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatch(List<Integralrecord> list) {
        return integralrecordMapper.updateBatch(list);
    }

    
    public int updateBatchSelective(List<Integralrecord> list) {
        return integralrecordMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<Integralrecord> list) {
        return integralrecordMapper.batchInsert(list);
    }

}
