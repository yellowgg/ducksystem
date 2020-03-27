package cn.yellowgg.ducksystem.entity;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:22
 */
@Data
@ApiModel(value = "课程视频信息", description = "一个课程多个视频")
public class CourseVideoInfo extends BaseEntity {
    private String title;
    private String videoUrl;
    private Long courseId;
    /**
     * 视频时长
     */
    private Integer duration;
}
