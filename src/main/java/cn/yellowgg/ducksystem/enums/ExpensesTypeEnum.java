package cn.yellowgg.ducksystem.enums;

/**
 * @Description: 流水类型枚举
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:31
 */
public enum ExpensesTypeEnum {
    REFUND(0, "退款", 1),
    RECHARGE(1, "充值", 0),
    BUYCLASS(2, "买课", 0),
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
