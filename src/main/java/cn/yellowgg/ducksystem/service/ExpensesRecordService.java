package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.entity.ExpensesRecord;
import cn.yellowgg.ducksystem.mapper.ExpensesRecordMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/22 00:43
 */
@Service
public class ExpensesRecordService {

    @Resource
    private ExpensesRecordMapper expensesrecordMapper;
    @org.springframework.beans.factory.annotation.Autowired
    private ExpensesRecordMapper expensesRecordMapper;


    public int deleteByPrimaryKey(Long id) {
        return expensesrecordMapper.deleteByPrimaryKey(id);
    }


    public int insert(ExpensesRecord record) {
        return expensesrecordMapper.insert(record);
    }


    public int insertOrUpdate(ExpensesRecord record) {
        return expensesrecordMapper.insertOrUpdate(record);
    }


    public int insertOrUpdateSelective(ExpensesRecord record) {
        return expensesrecordMapper.insertOrUpdateSelective(record);
    }


    public int insertSelective(ExpensesRecord record) {
        return expensesrecordMapper.insertSelective(record);
    }


    public ExpensesRecord selectByPrimaryKey(Long id) {
        return expensesrecordMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(ExpensesRecord record) {
        return expensesrecordMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(ExpensesRecord record) {
        return expensesrecordMapper.updateByPrimaryKey(record);
    }


    public int updateBatch(List<ExpensesRecord> list) {
        return expensesrecordMapper.updateBatch(list);
    }


    public int updateBatchSelective(List<ExpensesRecord> list) {
        return expensesrecordMapper.updateBatchSelective(list);
    }


    public int batchInsert(List<ExpensesRecord> list) {
        return expensesrecordMapper.batchInsert(list);
    }

    public List<ExpensesRecord> findByWalletId(Long walletId) {
        return expensesRecordMapper.findByWalletId(walletId);
    }

}
