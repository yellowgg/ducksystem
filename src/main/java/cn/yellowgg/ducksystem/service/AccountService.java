package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.Account;
import cn.yellowgg.ducksystem.mapper.AccountMapper;
import cn.yellowgg.ducksystem.utils.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/14 15:36
 */
@Service
public class AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private RedisUtils redisUtils;

    public int deleteByPrimaryKey(Long id) {
        return accountMapper.deleteByPrimaryKey(id);
    }


    /**
     * 使用微信用户信息来注册
     */
    public int register(Account record) {
        // TODO yellowgg 开通钱包、积分啥啥的 还有签到
        int addAccountResult = accountMapper.insertOrUpdateSelective(record);
        if (addAccountResult > UtilConstants.Number.ZERO) {
            redisUtils.hPut(record.getOpenId(), "hasInfo", UtilConstants.Str.ONE);
        }
        return addAccountResult;
    }

    public int addRole(Account record) {
        int addAccountResult = accountMapper.insertOrUpdateSelective(record);
        if (addAccountResult > UtilConstants.Number.ZERO) {
            redisUtils.hPut(record.getOpenId(), "isAdmin", record.getIsAdmin().toString());
        }
        return addAccountResult;
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

    public Account findByOpenId(String openId) {
        return accountMapper.findByOpenId(openId);
    }


}
