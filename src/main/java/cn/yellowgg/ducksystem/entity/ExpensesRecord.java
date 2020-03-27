package cn.yellowgg.ducksystem.entity;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:41
 */
@Data
@ApiModel(value = "流水记录", description = "买课、充值、提现等都会有流水记录")
public class ExpensesRecord extends BaseEntity {
    private Long accountId;
    private BigDecimal amount;
    /**
     * 说明
     */
    private String description;
    /**
     * 消费类型
     */
    private Integer expenseType;
}
