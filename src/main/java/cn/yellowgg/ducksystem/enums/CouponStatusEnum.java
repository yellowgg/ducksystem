package cn.yellowgg.ducksystem.enums;

/**
 * @Description:优惠券状态枚举
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 14:39
 */
public enum CouponStatusEnum {

    AVAILABLE(1, "可使用"),
    UNAVAILABLE(2, "不可用"),
    EXPIRED(3, "已过期"),
    ;
    private Integer value;
    private String name;

    CouponStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
