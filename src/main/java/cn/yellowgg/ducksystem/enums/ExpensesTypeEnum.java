package cn.yellowgg.ducksystem.enums;

import cn.yellowgg.ducksystem.constant.UtilConstants;

/**
 * @Description: 流水类型枚举
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:31
 */
public enum ExpensesTypeEnum {
    REFUND(UtilConstants.Number.ZERO, "退款", UtilConstants.Number.ONE),
    RECHARGE(UtilConstants.Number.ONE, "充值", UtilConstants.Number.ZERO),
    BUYCLASS(UtilConstants.Number.TWO, "买课", UtilConstants.Number.ZERO),
    ;

    private Integer value;
    private String name;
    /**
     * 1表示入账，0表示出账
     */
    private Integer inAndOut;

    ExpensesTypeEnum(Integer value, String name, Integer inAndOut) {
        this.value = value;
        this.name = name;
        this.inAndOut = inAndOut;
    }
}
