package cn.yellowgg.ducksystem.enums;

/**
 * @Description: 课程类型枚举
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:14
 */
public enum CourseTypeEnum {

    FULLCLASS(0, "全程班"),
    FEATURESCLASS(1, "特色班"),
    EMPLOYCLASS(2, "就业班"),
    COMMONCLASS(3, "普通班"),
    COMBATCLASS(4, "实战班"),
    ;

    private Integer value;
    private String name;

    CourseTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
