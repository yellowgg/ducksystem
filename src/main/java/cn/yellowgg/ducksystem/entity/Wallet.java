package cn.yellowgg.ducksystem.entity;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:32
 */
@Data
@ApiModel(value = "个人钱包")
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
}
