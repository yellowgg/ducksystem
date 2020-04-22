package cn.yellowgg.ducksystem.service;

import cn.hutool.core.util.StrUtil;
import cn.yellowgg.ducksystem.entity.Course;
import cn.yellowgg.ducksystem.entity.ExpensesRecord;
import cn.yellowgg.ducksystem.entity.IntegralRecord;
import cn.yellowgg.ducksystem.entity.Wallet;
import cn.yellowgg.ducksystem.entity.association.AccountAndCourse;
import cn.yellowgg.ducksystem.enums.ExpensesTypeEnum;
import cn.yellowgg.ducksystem.exception.CustomException;
import cn.yellowgg.ducksystem.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/21 23:43
 */
@Service
public class WalletService {

    @Resource
    private WalletMapper walletMapper;
    @Resource
    IntegralrecordMapper integralrecordMapper;
    @Resource
    ExpensesRecordMapper expensesRecordMapper;
    @Resource
    AccountAndCourseMapper accountAndCourseMapper;
    @Resource
    CourseMapper courseMapper;

    /**
     * 交易
     */
    public int trading(Long accountId, BigDecimal amount, Long courseId) throws CustomException {
        // 找出钱包
        Wallet wallet = walletMapper.findByAccountId(accountId);
        if (Objects.isNull(wallet)) {
            throw new CustomException("钱包找不到，请重试");
        }
        // 充值
        if (Objects.isNull(courseId)) {
            recharge(wallet, amount);
        } else {
            // 消费
            useAmount(wallet, amount, courseMapper.selectByPrimaryKey(courseId), accountId);
        }
        // 保存钱包
        return walletMapper.updateByPrimaryKey(wallet);
    }

    /**
     * 消费
     */
    public void useAmount(Wallet wallet, BigDecimal amount, Course course, Long accountId) throws CustomException {
        if (Objects.isNull(wallet) || Objects.isNull(course)) {
            throw new CustomException("购买失败，请重试");
        }
        // 检查钱
        wallet.checkAdequate(amount);
        //  扣钱
        wallet.setBalance(wallet.getBalance().subtract(amount));
        //  累计消费
        wallet.setTotalConsumption(wallet.getTotalConsumption().add(amount));
        // 加积分
        wallet.setIntegral(wallet.getIntegral() + course.getIntegral());
        //  插入积分记录
        IntegralRecord integralrecord = new IntegralRecord(wallet.getId());
        integralrecord.setIntegral(course.getIntegral().longValue());
        integralrecord.setDescription(StrUtil.format(
                "{}：{}，+{}积分",
                ExpensesTypeEnum.BUYCLASS.getName(), course.getName(), integralrecord.getIntegral()));
        integralrecordMapper.insert(integralrecord);
        // 插入消费记录
        ExpensesRecord expensesRecord = new ExpensesRecord(wallet.getId(), amount, ExpensesTypeEnum.BUYCLASS);
        expensesRecord.setDescription(StrUtil.format(
                "{}：{},使用了{}元",
                ExpensesTypeEnum.BUYCLASS.getName(), course.getName(), amount));
        // 插入买课记录
        accountAndCourseMapper.insert(new AccountAndCourse(accountId, course.getId()));
        expensesRecordMapper.insert(expensesRecord);
    }

    /**
     * 充值
     */
    public void recharge(Wallet wallet, BigDecimal amount) throws CustomException {
        if (Objects.isNull(wallet)) {
            throw new CustomException("充值失败，请重试");
        }
        // 加钱
        wallet.setBalance(wallet.getBalance().add(amount));
        // 插入消费记录
        ExpensesRecord expensesRecord = new ExpensesRecord(wallet.getId(), amount, ExpensesTypeEnum.RECHARGE);
        expensesRecord.setDescription(StrUtil.format("{}：+{}元", ExpensesTypeEnum.RECHARGE.getName(), amount));
        expensesRecordMapper.insert(expensesRecord);
    }

    /**
     * 获取交易记录
     */
    public List<ExpensesRecord> getExpensesRecordList(Long accountId) {
        return expensesRecordMapper.findByWalletIdOrderByIdDesc(walletMapper.findIdByAccountId(accountId));
    }

    /**
     * 获取积分记录
     */
    public List<IntegralRecord> getIntegralRecordList(Long accountId) {
        return integralrecordMapper.findByWalletIdOrderByIdDesc(walletMapper.findIdByAccountId(accountId));
    }

    public int deleteByPrimaryKey(Long id) {
        return walletMapper.deleteByPrimaryKey(id);
    }


    public int insert(Wallet record) {
        return walletMapper.insert(record);
    }


    public int insertOrUpdate(Wallet record) {
        return walletMapper.insertOrUpdate(record);
    }


    public int insertOrUpdateSelective(Wallet record) {
        return walletMapper.insertOrUpdateSelective(record);
    }


    public int insertSelective(Wallet record) {
        return walletMapper.insertSelective(record);
    }


    public Wallet selectByPrimaryKey(Long id) {
        return walletMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(Wallet record) {
        return walletMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Wallet record) {
        return walletMapper.updateByPrimaryKey(record);
    }


    public int updateBatch(List<Wallet> list) {
        return walletMapper.updateBatch(list);
    }


    public int updateBatchSelective(List<Wallet> list) {
        return walletMapper.updateBatchSelective(list);
    }


    public int batchInsert(List<Wallet> list) {
        return walletMapper.batchInsert(list);
    }

    public Wallet findByAccountId(Long accountId) {
        return walletMapper.findByAccountId(accountId);
    }

}
