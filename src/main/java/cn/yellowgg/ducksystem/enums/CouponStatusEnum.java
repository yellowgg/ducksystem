package cn.yellowgg.ducksystem.enums;

import cn.yellowgg.ducksystem.constant.UtilConstants;

/**
 * @Description:优惠券状态枚举
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 14:39
 */
public enum CouponStatusEnum {

    AVAILABLE(UtilConstants.Number.ONE, "可使用"),
    UNAVAILABLE(UtilConstants.Number.TWO, "不可用"),
    EXPIRED(UtilConstants.Number.THREE, "已过期"),

    ;

    private Integer value;
    private String name;

    CouponStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
