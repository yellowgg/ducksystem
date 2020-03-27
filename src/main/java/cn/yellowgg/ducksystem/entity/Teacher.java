package cn.yellowgg.ducksystem.entity;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:11
 */
@Data
@ApiModel(value = "教师对象", description = "本系统中教师只作为展示用，因为是线上看视频，不参与对学生的操作")
public class Teacher extends BaseEntity {
    private String name;
    private Integer sex;
    private LocalDateTime birthday;
    /**
     * 学历
     */
    private String education;
    /**
     * 职称
     */
    private String jobTitle;
    /**
     * 介绍
     */
    private String introduction;
}
