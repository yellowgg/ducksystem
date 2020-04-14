package cn.yellowgg.ducksystem.enums;

import cn.yellowgg.ducksystem.constant.UtilConstants;

/**
 * @Description: 性别枚举
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 12:40
 */
public enum SexEnum {

    UNKNOW(UtilConstants.Number.ZERO, "未知"),
    MAN(UtilConstants.Number.ONE, "男"),
    WOMAN(UtilConstants.Number.TWO, "女"),

    ;

    private Integer value;
    private String sexStr;

    SexEnum(Integer value, String sexStr) {
        this.value = value;
        this.sexStr = sexStr;
    }

    public static String getSexStrByValue(Integer value) {
        for (SexEnum each : SexEnum.values()) {
            if (each.value.equals(value)) {
                return each.sexStr;
            }
        }
        return "性别异常";
    }
}
