package cn.yellowgg.ducksystem.entity;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:44
 */
@Data
@ApiModel(value = "签到记录表")
public class AttendeeRecord extends BaseEntity {
    private Long accountId;
    /**
     * 是否七天连签
     */
    private Boolean isSevenDays;
    /**
     * 月份
     */
    private LocalDate month;
    /**
     * 签到的日期
     * ,隔开
     */
    private String days;
    /**
     * 月份里总签到数
     */
    private Integer totalDays;
}
