package cn.yellowgg.ducksystem.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.yellowgg.ducksystem.entity.Account;
import java.util.List;
import cn.yellowgg.ducksystem.mapper.AccountMapper;
/**
 * @Description:  
 * @Author: yellowgg
 * @Date: Created in 2020/4/14 15:36
 */
@Service
public class AccountService{

    @Resource
    private AccountMapper accountMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return accountMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Account record) {
        return accountMapper.insert(record);
    }

    
    public int insertOrUpdate(Account record) {
        return accountMapper.insertOrUpdate(record);
    }

    
    public int insertOrUpdateSelective(Account record) {
        return accountMapper.insertOrUpdateSelective(record);
    }

    
    public int insertSelective(Account record) {
        return accountMapper.insertSelective(record);
    }

    
    public Account selectByPrimaryKey(Long id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Account record) {
        return accountMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Account record) {
        return accountMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatch(List<Account> list) {
        return accountMapper.updateBatch(list);
    }

    
    public int updateBatchSelective(List<Account> list) {
        return accountMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<Account> list) {
        return accountMapper.batchInsert(list);
    }

}
