package cn.yellowgg.ducksystem.entity;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:35
 */
@Data
@ApiModel(value = "课程收藏", description = "学生收藏课程,多对多")
public class CourseCollection extends BaseEntity {
    private Long accountId;
    private Long courseId;
}
