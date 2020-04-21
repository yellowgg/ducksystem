package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.Account;
import cn.yellowgg.ducksystem.entity.Wallet;
import cn.yellowgg.ducksystem.mapper.AccountMapper;
import cn.yellowgg.ducksystem.mapper.WalletMapper;
import cn.yellowgg.ducksystem.utils.RedisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    private WalletMapper walletMapper;

    @Resource
    private RedisUtils redisUtils;

    public int deleteByPrimaryKey(Long id) {
        return accountMapper.deleteByPrimaryKey(id);
    }


    /**
     * 使用微信用户信息来注册
     */
    public int register(Account record) {
        // TODO yellowgg 开通积分啥啥的 还有签到
        int addAccountResult = accountMapper.insertOrUpdateSelective(record);
        if (addAccountResult > UtilConstants.Number.ZERO) {
            redisUtils.hPut(record.getOpenId(), "hasInfo", UtilConstants.Str.ONE);
        }
        walletMapper.insertOrUpdateSelective(new Wallet(record.getId()));
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

    public PageInfo<Account> findByNickNameNotIsAdminwithPage(int page, int pageSize, String nickName) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(accountMapper.findByNickNameNotIsAdmin(nickName)
                .stream().filter(Account::hasInfo).collect(Collectors.toList()));
    }
}
