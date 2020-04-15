package cn.yellowgg.ducksystem.enums;

import cn.yellowgg.ducksystem.constant.UtilConstants;

/**
 * @Description: 课程类型枚举
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:14
 */
public enum CourseTypeEnum {

    FULLCLASS(UtilConstants.Number.ZERO, "全程班"),
    FEATURESCLASS(UtilConstants.Number.ONE, "特色班"),
    EMPLOYCLASS(UtilConstants.Number.TWO, "就业班"),
    COMMONCLASS(UtilConstants.Number.THREE, "普通班"),
    COMBATCLASS(UtilConstants.Number.FOUR, "实战班"),
    ;

    private Integer value;
    private String name;

    CourseTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
