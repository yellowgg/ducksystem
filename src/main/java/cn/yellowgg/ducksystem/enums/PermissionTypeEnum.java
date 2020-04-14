package cn.yellowgg.ducksystem.enums;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 权限资源类型枚举
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 10:12
 */
public enum PermissionTypeEnum {
    DIRECTORY(UtilConstants.Number.ONE, "目录"),
    MENU(UtilConstants.Number.TWO, "菜单"),
    BUTTON(UtilConstants.Number.THREE, "按钮"),
    ;

    @Setter
    @Getter
    private Integer value;
    @Setter
    @Getter
    private String name;

    PermissionTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
