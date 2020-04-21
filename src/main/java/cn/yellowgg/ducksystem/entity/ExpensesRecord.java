package cn.yellowgg.ducksystem.entity;

import cn.hutool.core.date.DateUtil;
import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import cn.yellowgg.ducksystem.enums.ExpensesTypeEnum;
import com.fasterxml.jackson.annotation.JsonGetter;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/22 00:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "流水记录", description = "买课、充值、提现等都会有流水记录")
public class ExpensesRecord extends BaseEntity {
    private Long walletId;

    private BigDecimal amount;

    /**
     * 说明
     */
    private String description;

    /**
     * 消费类型
     */
    private Integer expenseType;

    public ExpensesRecord(Long walletId, BigDecimal amount, ExpensesTypeEnum expenseType) {
        this.walletId = walletId;
        this.amount = amount;
        this.expenseType = expenseType.getValue();
    }

    @JsonGetter("createTime")
    public String createShow() {
        return DateUtil.format(getGmtCreate(), UtilConstants.DateFormatStr.NORM_DATETIME_PATTERN);
    }

}