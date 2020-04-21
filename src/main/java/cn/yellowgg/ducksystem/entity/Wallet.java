package cn.yellowgg.ducksystem.entity;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:32
 */
@Data
@ApiModel(value = "个人钱包")
@EqualsAndHashCode(callSuper = true)
public class Wallet extends BaseEntity {
    private Long accountId;
    /**
     * 余额
     */
    private BigDecimal balance;
    /**
     * 累计消费
     */
    private BigDecimal totalConsumption;
    /**
     * 积分
     */
    private Integer integral;

    public Wallet(Long accountId) {
        this.accountId = accountId;
        this.balance = BigDecimal.ZERO;
        this.totalConsumption = BigDecimal.ZERO;
        this.integral = UtilConstants.Number.ZERO;
    }
}
