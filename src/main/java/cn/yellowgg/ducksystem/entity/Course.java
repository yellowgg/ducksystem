package cn.yellowgg.ducksystem.entity;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:12
 */
@Data
@ApiModel(value = "课程对象", description = "课程的一些信息")
public class Course extends BaseEntity {
    private String name;
    private Integer type;
    private BigDecimal price;
    /**
     * 积分
     */
    private Integer integral;
    /**
     * 详情
     */
    private String details;
    /**
     * 特色
     */
    private String characteristical;
    /**
     * 简介
     */
    private String Introduction;
}
