package cn.yellowgg.ducksystem.enums;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import com.google.common.collect.Maps;

import java.util.Map;

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

    public static String getNameByValue(Integer value) {
        for (CourseTypeEnum each : CourseTypeEnum.values()) {
            if (each.value.equals(value)) {
                return each.name;
            }
        }
        return "课程类型异常";
    }

    public static Map<Integer, String> getValueAndNameMap() {
        Map<Integer, String> map = Maps.newHashMap();
        for (CourseTypeEnum each : CourseTypeEnum.values()) {
            map.put(each.value, each.name);
        }
        return map;
    }
}
