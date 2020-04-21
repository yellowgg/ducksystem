package cn.yellowgg.ducksystem.entity;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/4/21 17:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CourseVideoInfo extends BaseEntity {
    @NotNull(message = "绑定哪个课程")
    private Long courseId;

    @NotNull(message = "时长必须告诉我")
    private Integer duration;

    @NotBlank(message = "视频标题告诉我")
    private String title;

    @NotBlank(message = "视频都没有你怎么看")
    private String videoUrl;

    @NotBlank(message = "给个封面吧")
    private String cover;

    @NotBlank(message = "给个名字吧")
    private String courseName;
}