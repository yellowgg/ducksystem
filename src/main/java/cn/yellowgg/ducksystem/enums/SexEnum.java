package cn.yellowgg.ducksystem.enums;

/**
 * @Description: 性别枚举
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 12:40
 */
public enum SexEnum {

    MAN(0, "男"),
    WOMAN(1, "女"),

    ;

    private Integer value;
    private String sexStr;

    SexEnum(Integer value, String sexStr) {
        this.value = value;
        this.sexStr = sexStr;
    }
}
