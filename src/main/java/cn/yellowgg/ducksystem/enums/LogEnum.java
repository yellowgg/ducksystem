package cn.yellowgg.ducksystem.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 日志枚举
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 11:06
 */
public enum LogEnum {

    /**
     * 业务
     */
    BUSSINESS("bussiness"),
    /**
     * 平台
     */
    PLATFORM("platform"),
    /**
     * 数据
     */
    DB("db"),
    /**
     * 异常
     */
    EXCEPTION("exception"),

    ;

    @Setter
    @Getter
    private String category;

    LogEnum(String category) {
        this.category = category;
    }
}
